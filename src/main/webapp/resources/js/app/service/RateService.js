/**
 * Created by Александр on 08.11.2016.
 */
var RateService = (function () {

    var methods = {
        loadRates: function (callback) {
            $.ajax({
                url: "/api/rate",
                type: "GET",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot load rates");
                }
            });
        }

    };

    return methods;

}());