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
            var $content = $('<a>', {href: "/../Sample/car?user_id=" + id});
            var $button = $('<button>', {type: "button", class: "close remove"});
            var $symbol = String.fromCharCode(215);
            var $span = $('<span aria-hidden="true">');
            if(car.images.length > 0) {
                var image = document.createElement('img');
                var imgSrc = car.images[0].carImagePath;
                image.src = "../.." + imgSrc;
                image.width=100;
                image.height=100;
                image.alt="here should be some image";
            }


            $span.append($symbol);

            $button.append($span);
            $button.attr("user-id", id);

            $content.append(description + " " + car.make + " " + car.model);
            alert("image: " + image);
            if(image != undefined) {
                $content.append(image);
            }
            $body.append($content);
            $body.append($button);
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
