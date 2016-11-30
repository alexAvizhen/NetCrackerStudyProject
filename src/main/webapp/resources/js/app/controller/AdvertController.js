/**
 * Created by Александр on 09.11.2016.
 */
$(function () {

    var advertCtrl = (function () {
        var advert;
        var defaultAbbr = 'BYN';
        var methods = {
            init: function () {
                this.showAdvert();
                this.initCurrencySelect();
                this.bindEvents();
            },
            showAdvert: function () {
                var lang = $("#locale").val();
                setLang(lang, function() {});
                AdvertService.loadAdvert($("#advertContainer").attr("advert-id"), function (data) {
                    advert = data;
                    render(advert);
                    updatePrice(defaultAbbr);
                });
            },
            initCurrencySelect: function() {
                var currencySelect = createCurrencySelect();
                currencySelect.change(function() {
                    var currencyAbbr = currencySelect.val();
                    updatePrice(currencyAbbr);
                });
                var currency = $.i18n.prop('currency');
                $('#currencySelectContainer').append(currency + ": ");
                $('#currencySelectContainer').append(currencySelect);
            },
            bindEvents: function() {
                $("#addAdvertToCartForm").submit(function (e) {
                    e.preventDefault();
                    var advertId = $("#advertId").val();
                    CartService.addAdvertToCart(advertId, function(data) {
                        if(+data == -1 ) {
                            $("#addAdvertToCartMsg").empty();
                            $("#addAdvertToCartMsg").append("<p>" + $.i18n.prop('advert.notFound') + "</p>");
                            return;
                        }
                        $("#cartSize").empty();
                        $("#cartSize").append(data);
                        $("#addAdvertToCartMsg").empty();
                        var advertWasAdded = $.i18n.prop('advert.wasAdded');
                        $("#addAdvertToCartMsg").append("<p>" + advertWasAdded + "</p>");
                    });
                });
            }

        };

        function createPanel(advert) {
            if (advert == null) {
                var notFoundPanel = $('<div>', {class: 'panel panel-default', id: 'panel'});
                notFoundPanel.append("<h3>"+ $.i18n.prop('advert.notFound') + "</h3>");
                return notFoundPanel;
            }
            var $panel = $('<div>', {class: 'panel panel-default', id: 'panel' + advert.id});
            var $body = $('<div>', {class: 'panel-body'});
            var $content = $('<div>', {class: 'row'});
            var imageDiv1 = $('<div>', {class: 'col-md-5 col-lg-5'});
            var imageDiv2 = $('<div>', {class: 'col-md-5 col-lg-5'});
            var car = advert.car;

            CarService.loadCarImages(car.id, function (data) {
                var carImages = data;
                if(carImages != undefined && carImages.length > 0) {
                    var image;
                    var imgSrc;
                    for(var i = 0; i < carImages.length; i++) {
                        image = document.createElement('img');
                        imgSrc = carImages[i].carImagePath;
                        image.classList.add("img-responsive");
                        image.src = "../.." + imgSrc;
                        image.alt="here should be some image";
                        if(i % 2 == 0) {
                            imageDiv1.append(image);
                        } else {
                            imageDiv2.append(image);
                        }
                    }
                }
            });

            var contentDiv = $('<div>', {class: 'col-md-9 col-lg-9'});
            contentDiv.append( $.i18n.prop('car.make')+ ": " + car.make + "<br>");
            contentDiv.append($.i18n.prop('car.model')+ ": " + car.model + "<br>");
            contentDiv.append($.i18n.prop('car.condition')+ ": " + car.condition + "<br>");
            contentDiv.append($.i18n.prop('car.year')+ ": " + car.year + "<br>");
            var priceSpan = $('<span>', {id: 'priceSpan'});
            contentDiv.append(priceSpan);
            contentDiv.append($.i18n.prop('car.description')+ ": " + car.description + "<br>");
            contentDiv.append($.i18n.prop('advert.description')+ ": " + advert.description + "<br>");
            $content.append(contentDiv);
            var carImages = $.i18n.prop('car.images')
            $content.append("<div class='col-md-9 col-lg-9'><h3>" + carImages + "</h3></div>")
            $content.append(imageDiv1);
            $content.append(imageDiv2);

            $body.append($content);
            $panel.append($body);
            return $panel;
        }

        function setLang(lang, callback) {
            $.i18n.properties({
                name: 'msg',
                path: '../resources/i18n/',
                mode: 'map',
                language: lang,
                callback: function() {
                    callback();
                }
            });
        }
        function updatePrice(abbr) {
            $('#priceSpan').empty();
            var car = advert.car;
            if (abbr == "BYN") {
                $('#priceSpan').append($.i18n.prop('car.price') + ": " + car.price + " " +abbr+ "<br>");
            } else {
                convertBYNTo(car.price, abbr, function(res) {
                    $('#priceSpan').append($.i18n.prop('car.price') + ": " + res + " " +abbr+ "<br>");
                });
            }

        }

        function convertBYNTo(price, abbr, callback) {
            RateService.convertPriceTo(price, abbr, function(res) {
                callback(res);
            });
        }

        function createCurrencySelect() {
            var currencySelect = $('<select>');
            RateService.loadAllCurrencies(function(data) {
                var option = $('<option>');
                option.append("BYN");
                currencySelect.append(option);
                for(var i = 0; i < data.length; i++) {
                    option = $('<option>');
                    option.append(data[i]);
                    currencySelect.append(option);
                }
            });
            return currencySelect;
        }

        function render(advert) {
            var panel = createPanel(advert);
            $('#advertContainer').append(panel);
        }

        function removePanel(id) {
            $('#' + id).remove();
        }

        return methods;
    }());

    advertCtrl.init();

});
