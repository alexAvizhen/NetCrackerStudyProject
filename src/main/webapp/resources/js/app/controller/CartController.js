/**
 * Created by Александр on 23.11.2016.
 */
$(function () {

    var cartCtrl = (function () {
        var methods = {
            init: function () {
                this.bindEvents();
            },
            bindEvents: function () {
                $(".removeAdvertFromCart").click(function(e) {
                    var advertId = this.getAttribute("advert-id");
                    CartService.removeAdvertFromCart(advertId, function(data) {
                        if(+data == -1) {
                            $("#removeAdvertFromCartMsg" + advertId).empty();
                            $("#removeAdvertFromCartMsg" + advertId).append("advert wasn't found");
                            return;
                        }
                        $("#cartSize").empty();
                        $("#cartSize").append(data);
                        $("#removeAdvertFromCartMsg" + advertId).empty();
                        $("#removeAdvertFromCartMsg" + advertId).append("advert was removed successful");
                        $("#advertDiv" + advertId).remove();
                    });
                });
            }

        };
        return methods;
    }());

    cartCtrl.init();

});