/**
 * Created by Александр on 07.11.2016.
 */
var AdvertService = (function () {

    var methods = {
        loadAdverts: function (callback) {
            $.ajax({
                url: "/api/advert",
                method: "GET",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot load adverts");
                }
            });
        },
        loadAdvert: function (advertId, callback) {
            $.ajax({
                url: "/api/advert/" + advertId,
                method: "GET",
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
