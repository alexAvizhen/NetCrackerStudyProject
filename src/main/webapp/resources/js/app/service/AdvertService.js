/**
 * Created by Александр on 07.11.2016.
 */
var AdvertService = (function () {

    var methods = {
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
        loadFilteringPageWithOrderedAdverts: function (orderCriteria, searchCriteria, callback) {
            var criteria = {orderCriteria: orderCriteria, searchCriteria: searchCriteria};
            $.ajax({
                url: "/api/advert/page/search",
                type: "POST",
                data: JSON.stringify(criteria),
                dataType : 'json',
                contentType : "application/json",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot load page with adverts");
                }
            });

        }

    };

    return methods;

}());
