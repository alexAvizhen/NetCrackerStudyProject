/**
 * Created by Александр on 07.11.2016.
 */
$(function () {

    var advertCtrl = (function () {
        var adverts;
        var methods = {
            init: function () {
                this.showAdverts();
            },
            showAdverts: function () {
                AdvertService.loadAdverts(function (data) {
                    adverts = data;
                    render(adverts);
                });
            }

        };

        function createPanel(description, id, car) {
            var $panel = $('<div>', {class: 'panel panel-default', id: 'panel' + id});
            var $body = $('<div>', {class: 'panel-body'});
            var $content = $('<div>', {class: 'row'});
            var $link = $('<a>', {href: "/advert/" + id});
            var imageDiv = $('<div>', {class: 'col-md-4 col-lg-3'});
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
            contentDiv.append(description + "<br>");
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
            adverts.forEach(function (advert) {
                var panel = createPanel(advert.description, advert.id, advert.car);
                $('#advertContainer').append(panel);
            });
        }

        function removePanel(id) {
            $('#' + id).remove();
        }

        return methods;
    }());

    advertCtrl.init();

});
