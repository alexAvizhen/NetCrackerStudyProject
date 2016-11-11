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
        },
        loadAdvertsByMakeByYearBetweenByPriceBetween: function (make, yearFrom, yearTo, priceFrom,
                                                               priceTo, callback) {
            $.ajax({
                url: "/api/advert/search",
                type: "GET",
                data: { make: make, yearFrom: yearFrom, yearTo: yearTo,
                    priceFrom: priceFrom, priceTo: priceTo},
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
