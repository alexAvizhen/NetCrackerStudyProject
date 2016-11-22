/**
 * Created by Александр on 09.11.2016.
 */
$(function () {

    var advertCtrl = (function () {
        var advert;
        var methods = {
            init: function () {
                this.showAdvert();
            },
            showAdvert: function () {
                AdvertService.loadAdvert($("#advertContainer").attr("advert-id"), function (data) {
                    advert = data;
                    render(advert);
                });
            },

        };

        function createPanel(advert) {
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
            contentDiv.append("Name: " + car.make + " " + car.model + "<br>");
            contentDiv.append("Car condition: " + car.condition + "<br>");
            contentDiv.append("Year: " + car.year + "<br>");
            contentDiv.append("Price: " + car.price + " BR <br>");
            contentDiv.append("Car description: " + car.description + "<br>");
            contentDiv.append("Advert description: " + advert.description + "<br>");
            $content.append(contentDiv);

            $content.append("<div class='col-md-9 col-lg-9'><h3> Car images </h3></div>")
            $content.append(imageDiv1);
            $content.append(imageDiv2);

            $body.append($content);
            $panel.append($body);
            return $panel;
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
