/**
 * Created by Александр on 08.11.2016.
 */
var RateService = (function () {

    var methods = {
        loadRates: function (callback) {
            $.ajax({
                url: "/api/rate",
                method: "GET",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot load rates");
                }
            });
        },
        loadAllCurrencies: function (callback) {
            $.ajax({
                url: "/api/rate/currencies",
                method: "GET",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot load currencies");
                }
            });
        },
        convertPriceTo: function (price, abbr, callback) {
            $.ajax({
                url: "/api/rate/convert?price=" + price + "&abbr=" + abbr,
                method: "GET",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot convert price");
                }
            });
        }

    };

    return methods;

}());