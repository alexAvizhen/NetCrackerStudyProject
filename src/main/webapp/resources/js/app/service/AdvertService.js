/**
 * Created by Александр on 07.11.2016.
 */
var AdvertService = (function () {

    var methods = {
        loadAdverts: function (callback) {
            $.ajax({
                url: "/api/advert",
                type: "GET",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot load adverts");
                }
            });
        }

    };

    return methods;

}());
