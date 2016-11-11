/**
 * Created by Александр on 09.11.2016.
 */
var CarService = (function () {

    var methods = {
        loadCarImage: function (carId, callback) {
            $.ajax({
                url: "/api/car/" + carId + "/image",
                type: "get",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot load image");
                }
            });
        },
        loadCarImages: function (carId, callback) {
            $.ajax({
                url: "/api/car/" + carId + "/images",
                type: "get",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot load images");
                }
            });
        },
        loadCarMakes : function (callback) {
            $.ajax({
                url: "/api/car/makes",
                type: "get",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot load images");
                }
            });
        }

    };

    return methods;

}());