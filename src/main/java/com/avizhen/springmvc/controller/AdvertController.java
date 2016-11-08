package com.avizhen.springmvc.controller;

import com.avizhen.entity.Advert;
import com.avizhen.service.AdvertService;
import com.avizhen.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        List<Advert> res = advertService.findAllAdverts();
        return res;
    }
}
