/**
 * Created by Александр on 19.11.2016.
 */
$(function () {

    var advertCtrl = (function () {
        var advert;
        var methods = {
            init: function () {
                this.initAdvert();
                this.initFormInputClass();
                this.bindEvents();
            },
            initAdvert: function () {
                var advertId = +$("#advertId").val();
                if(advertId > 0) {
                    AdvertService.loadAdvert(advertId, function(data) {
                        advert = data;
                        initFormInputs(advert);
                    });
                }
            },
            initFormInputClass: function() {
                $(".positive-integer").numeric({ decimal: false, negative: false },
                    function() { alert("Positive integers only");
                        this.value = "";
                        this.focus(); });
            },
            bindEvents: function() {
                $("#editForm").submit(function (e) {
                    e.preventDefault();
                    initAdvert();
                    AdvertService.saveAdvert(advert,
                        function(data) {
                            $("#msg").empty();
                            if($("#advertId").val() > 0) {
                                $("#msg").append("Advert was edited successful");
                            } else {
                                $("#msg").append("Advert was added successful");
                            }
                        },
                        function(error) {
                            $("#msg").empty();
                            if($("#advertId").val() > 0) {
                                $("#msg").append("Advert wasn't edited successful");
                            } else {
                                $("#msg").append("Advert wasn't added successful");
                            }
                        }
                    );
                });
            }
        };

        function initAdvert() {
            var car;
            if(advert == undefined) {
                advert = {};
                car = {};
            } else {
                car = advert.car;
            }

            advert.description = $("#advertDescription").val();
            var carId = +$("#carId").val();
            if(carId > 0 ) {
                car.id = carId;
            }
            car.make = $("#carMake").val();
            car.model = $("#carModel").val();
            car.year = $("#carYear").val();
            car.price = $("#carPrice").val();
            car.description = $("#carDescription").val();
            car.condition = $("#carCondition").val();

            advert.car = car;
            var advertId = +$("#advertId").val();
            if (advertId > 0) {
                advert.id = advertId;
            }
        }
        function initFormInputs(advert) {
            if (advert != undefined) {
                $("#advertDescription").val(advert.description);
                var car = advert.car;
                $("#carMake").val(car.make);
                $("#carModel").val(car.model);
                $("#carPrice").val(car.price);
                $("#carYear").val(car.year);
                $("#carDescription").val(car.description);
                $("#carCondition").val(car.condition);
            } else {
                alert("advert undefined");
            }
        }
        function removePanel(id) {
            $('#' + id).remove();
        }

        return methods;
    }());

    advertCtrl.init();

});
