package com.avizhen.springmvc.controller.rest;

import com.avizhen.entity.Advert;
import com.avizhen.service.AdvertService;
import com.avizhen.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Александр on 22.11.2016.
 */
@RestController
@RequestMapping(value = "/api")
@SessionAttributes({"cart"})
public class CartRestController {
    @Autowired
    private AdvertService advertService;

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    @JsonView(Views.Public.class)
    public int addAdvertToCart(@RequestBody int advertId, @ModelAttribute("cart") List<Advert> cart) {
        Advert advert = advertService.findAdvertById(advertId);
        cart.add(advert);
        return cart.size();
    }

    @RequestMapping(value = "/cart/remove", method = RequestMethod.POST)
    @JsonView(Views.Public.class)
    public int removeAdvertFromCart(@RequestBody int advertId, @ModelAttribute("cart") List<Advert> cart) {
        Advert advert = advertService.findAdvertById(advertId);
        if (cart.remove(advert)) {
            return cart.size();
        }
        return -1;
    }


}
