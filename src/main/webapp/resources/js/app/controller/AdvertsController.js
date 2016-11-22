/**
 * Created by Александр on 07.11.2016.
 */
$(function () {

    var advertCtrl = (function () {
        var adverts;
        var makes;
        var page;
        var orderCriteria;
        var searchCriteria;
        var methods = {
            init: function () {
                this.initAdvert();
                this.initCarMakesSelect();
                this.initFormInputClass();
                this.bindEvents();
            },
            initAdvert: function () {
                initSearchCriteria();
                initOrderCriteria();
                AdvertService.loadFilteringPageWithOrderedAdverts(orderCriteria, searchCriteria, renderPage);
            },
            initCarMakesSelect: function () {
                CarService.loadCarMakes(function (data) {
                    makes = data;
                    renderCarMakesSelect(makes);
                });
            },
            initFormInputClass: function() {
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
                    initSearchCriteria(searchCriteria);
                    orderCriteria.pageNumber = 0;
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
                $("#pageNumberSelect").change(function() {
                    orderCriteria.pageNumber = +$("#pageNumberSelect option:selected").val() - 1;
                    AdvertService.loadFilteringPageWithOrderedAdverts(orderCriteria, searchCriteria, renderPage);
                });
                $('#prevPageBtn').click(function() {
                    orderCriteria.pageNumber--;
                    AdvertService.loadFilteringPageWithOrderedAdverts(orderCriteria, searchCriteria, renderPage);
                });
                $('#nextPageBtn').click(function() {
                    orderCriteria.pageNumber++;
                    AdvertService.loadFilteringPageWithOrderedAdverts(orderCriteria, searchCriteria, renderPage);
                });
            }
        };

        function renderPage(data) {
            page = data;
            adverts = page.content;
            renderAdverts(page);
            renderPaginationControls(page);
        };

        function createPanel(advert, advertIndex) {
            var $panel = $('<div>', {class: 'panel panel-default', id: 'panel' + advert.id});
            var $body = $('<div>', {class: 'panel-body'});
            var $content = $('<div>', {class: 'row'});
            var $link = $('<a>', {href: "/advert/" + advert.id, target: "_blank"});
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

            var indexDiv = $('<div>', {class: 'col-md-1 col-lg-1'});
            indexDiv.append("<h2>" + advertIndex + "</h2>");
            $content.append(indexDiv);

            $content.append(imageDiv);

            var contentDiv = $('<div>', {class: 'col-md-5 col-lg-6'});
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

        function renderAdverts(page) {
            var adverts = page.content;
            $('#advertContainer').empty();
            for (var i = 0; i < adverts.length; i++) {
                var advert = adverts[i];
                var panel = createPanel(advert, page.number * page.size + i + 1);
                $('#advertContainer').append(panel);
            }
        }

        function renderCarMakesSelect(makes) {
            $('#carMakesSelect').empty();
            var $option = $('<option>', {value: "defaultValue"});
            $option.append("Любая");
            $('#carMakesSelect').append($option);
            makes.forEach(function(make) {
                $option = $('<option>');
                $option.append(make);
                $('#carMakesSelect').append($option);
            });
        }

        function initSearchCriteria() {
            if(searchCriteria == undefined) {
                searchCriteria = {};
            }
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
        }

        function initOrderCriteria() {
            if(orderCriteria == undefined) {
                orderCriteria = {};
            }
            orderCriteria.pageNumber = 0;
            orderCriteria.pageSize = +$("#pageSizeSelect option:selected").val();
            orderCriteria.sortField = $("#sortAdvertSelect option:selected").attr("field");
            orderCriteria.sortDirection = $("#sortAdvertSelect option:selected").attr("direction");
        }

        function renderPaginationControls(page) {
            var prevPageBtn = $("#prevPageBtn");
            var nextPageBtn = $("#nextPageBtn");
            if (page.hasPrevious) {
                prevPageBtn.attr("disabled", false);
            } else{
                prevPageBtn.attr("disabled", true);
            }

            if(page.hasNext) {
                nextPageBtn.attr("disabled", false);
            } else {
                nextPageBtn.attr("disabled", true);
            }
            var select = $("#pageNumberSelect");
            select.empty();
            if(page.totalPages > 0) {
                var option;
                for (var i = 1; i <= +page.totalPages; i++) {
                    if ((i - 1) == +page.number) {
                        option = $('<option selected>');
                    } else{
                        option = $('<option>');
                    }
                    option.text(i);
                    select.append(option);
                }
            }

        }

        function removePanel(id) {
            $('#' + id).remove();
        }

        return methods;
    }());

    advertCtrl.init();

});
