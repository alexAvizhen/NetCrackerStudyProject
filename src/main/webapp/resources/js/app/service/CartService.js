/**
 * Created by Александр on 22.11.2016.
 */

var CartService = (function () {

    var methods = {
        addAdvertToCart: function (advertId, callback) {
            $.ajax({
                url: "/api/cart",
                method: "POST",
                data: JSON.stringify(advertId),
                dataType : 'json',
                contentType : "application/json",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot add advert to cart");
                }
            });
        },
        removeAdvertFromCart: function (advertId, callback) {
            $.ajax({
                url: "/api/cart/remove",
                method: "POST",
                data: JSON.stringify(advertId),
                dataType : 'json',
                contentType : "application/json",
                success: function (data) {
                    callback(data);
                },
                error: function () {
                    console.error("cannot remove advert from cart");
                }
            });
        }

    };

    return methods;

}());