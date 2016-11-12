/**
 * Created by Александр on 07.11.2016.
 */
$(function () {

    var advertCtrl = (function () {
        var adverts;
        var makes;
        var page;
        var pageSize;
        var sortField;
        var sortDirection;
        var pageNumber;
        var orderCriteria;
        var searchCriteria;
        var methods = {
            init: function () {
                this.showAdverts();
                this.initCarMakesSelect();
                this.initFormInputs();
                this.bindEvents();
            },
            showAdverts: function () {
                searchCriteria = {make: "", yearFrom: -1, yearTo: -1, priceFrom: -1, priceTo: -1};
                pageNumber = 0;
                pageSize = +$("#pageSizeSelect option:selected").val();
                sortField = $("#sortAdvertSelect option:selected").attr("field");
                sortDirection = $("#sortAdvertSelect option:selected").attr("direction");
                orderCriteria = { pageNumber: pageNumber, pageSize: pageSize, sortField: sortField,
                    sortDirection: sortDirection};
                AdvertService.loadFilteringPageWithOrderedAdverts(orderCriteria, searchCriteria, renderPage);

            },
            initCarMakesSelect: function () {
                CarService.loadCarMakes(function (data) {
                    makes = data;
                    renderCarMakesSelect(makes);
                });
            },
            initFormInputs: function() {
                $(".positive").numeric({ negative: false },
                    function() { alert("No negative values");
                        this.value = "";
                        this.focus();
                    });
                $(".positive-integer").numeric({ decimal: false, negative: false },
                    function() { alert("Positive integers only");
                        this.value = "";
                        this.focus(); });
            },
            bindEvents: function () {
                $('#clearBtn').click(function(e) {
                    $('#carMakesSelect').val("Любая");
                    $('#priceFrom').val("");
                    $('#priceTo').val("");
                    $('#yearFrom').val("");
                    $('#yearTo').val("");
                });
                $('#findCarsForm').submit(function (e) {
                    e.preventDefault();
                    var make = $("#carMakesSelect option:selected").text();
                    make = make == "Любая" ? "" : make;
                    searchCriteria.make = make;
                    var yearFrom = $("#yearFrom").val();
                    yearFrom = yearFrom == "" ? -1 : +yearFrom;
                    searchCriteria.yearFrom = yearFrom;
                    var yearTo = $("#yearTo").val();
                    yearTo = yearTo == "" ? -1 : +yearTo;
                    searchCriteria.yearTo = yearTo;
                    var priceFrom = $("#priceFrom").val();
                    priceFrom = priceFrom == "" ? -1 : +priceFrom;
                    searchCriteria.priceFrom = priceFrom;
                    var priceTo = $("#priceTo").val();
                    priceTo = priceTo == "" ? -1 : +priceTo;
                    searchCriteria.priceTo = priceTo;
                    AdvertService.loadFilteringPageWithOrderedAdverts(orderCriteria, searchCriteria, renderPage);
                });

                $("#pageSizeSelect").change(function() {
                    orderCriteria.pageSize = +$("#pageSizeSelect option:selected").val();
                    orderCriteria.pageNumber = 0;
                    AdvertService.loadFilteringPageWithOrderedAdverts(orderCriteria, searchCriteria, renderPage);
                });
                $("#sortAdvertSelect").change(function() {
                    orderCriteria.sortField = $("#sortAdvertSelect option:selected").attr("field");
                    orderCriteria.sortDirection = $("#sortAdvertSelect option:selected").attr("direction");
                    orderCriteria.pageNumber = 0;
                    AdvertService.loadFilteringPageWithOrderedAdverts(orderCriteria, searchCriteria, renderPage);
                });
            }
        };

        function renderPage(data) {
            $('#advertContainer').empty();
            page = data;
            adverts = page.content;
            render(adverts);
        };

        function createPanel(advert) {
            var $panel = $('<div>', {class: 'panel panel-default', id: 'panel' + advert.id});
            var $body = $('<div>', {class: 'panel-body'});
            var $content = $('<div>', {class: 'row'});
            var $link = $('<a>', {href: "/advert/" + advert.id});
            var imageDiv = $('<div>', {class: 'col-md-4 col-lg-3'});
            var car = advert.car;
            CarService.loadCarImage(car.id, function (data) {
                var presentationCarImage = data;
                if(presentationCarImage != undefined) {
                    var image = document.createElement('img');
                    var imgSrc = presentationCarImage.carImagePath;
                    image.src = "../.." + imgSrc;
                    image.width = 200;
                    image.height = 150;
                    image.alt="here should be some image";
                    imageDiv.append(image);
                }
            });

            $link.append("Подробнее");

            $content.append(imageDiv);

            var contentDiv = $('<div>', {class: 'col-md-6 col-lg-7'});
            contentDiv.append(car.make + " " + car.model + "<br>");
            contentDiv.append(advert.description + "<br>");
            contentDiv.append("Year: " + car.year + "<br>");
            contentDiv.append(car.price + " BR <br>");
            $content.append(contentDiv);

            var linkDiv = $('<div>', {class: 'col-md-2 col-lg-2'});
            linkDiv.append($link);
            $content.append(linkDiv);

            $body.append($content);
            $panel.append($body);
            return $panel;
        }

        function render(adverts) {
            for (var i = 0; i < adverts.length; i++) {
                var advert = adverts[i];
                var panel = createPanel(advert);
                $('#advertContainer').append(panel);
            }
        }

        function renderCarMakesSelect(makes) {
            var $option = $('<option>', {value: "defaultValue"});
            $option.append("Любая");
            $('#carMakesSelect').append($option);
            makes.forEach(function(make) {
                $option = $('<option>');
                $option.append(make);
                $('#carMakesSelect').append($option);
            });
        }

        function removePanel(id) {
            $('#' + id).remove();
        }

        return methods;
    }());

    advertCtrl.init();

});
