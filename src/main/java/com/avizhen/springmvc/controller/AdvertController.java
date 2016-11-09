package com.avizhen.springmvc.controller;

import com.avizhen.entity.Advert;
import com.avizhen.service.AdvertService;
import com.avizhen.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Александр on 07.11.2016.
 */

@RestController
@RequestMapping(value = "/api")
public class AdvertController {
    @Autowired
    private AdvertService advertService;


    @RequestMapping(value = "/advert", method = RequestMethod.GET)
    @Transactional
    @JsonView(Views.Public.class)
    public List<Advert> findAllAdverts() {
        return advertService.findAllAdverts();
    }

    @RequestMapping(value = "/advert/{advertId}", method = RequestMethod.GET)
    @JsonView(Views.Public.class)
    public Advert findAdvert(@PathVariable int advertId) {
        return advertService.findAdvertById(advertId);
    }

    @RequestMapping(value = "/advert", method = RequestMethod.POST)
    @JsonView(Views.Public.class)
    public Advert addAdvert(@RequestBody Advert advert) {
        return advertService.addAdvert(advert);
    }

    @RequestMapping(value = "/advert/{advertId}", method = RequestMethod.DELETE)
    public void removeAdvert(@PathVariable int advertId) {
        advertService.removeAdvertById(advertId);
    }
}
