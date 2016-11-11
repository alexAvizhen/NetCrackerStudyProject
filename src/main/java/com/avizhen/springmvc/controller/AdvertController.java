package com.avizhen.springmvc.controller;

import com.avizhen.entity.Advert;
import com.avizhen.entity.Car;
import com.avizhen.service.AdvertService;
import com.avizhen.service.CarService;
import com.avizhen.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 07.11.2016.
 */

@RestController
@RequestMapping(value = "/api")
public class AdvertController {
    @Autowired
    private AdvertService advertService;

    @Autowired
    private CarService carService;


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

    @RequestMapping(value = "/advert/search", method = RequestMethod.GET)
    @Transactional
    @JsonView(Views.Public.class)
    public List<Advert> findFilterAdverts(@RequestParam("make") String make, @RequestParam("yearFrom") int yearFrom,
            @RequestParam("yearTo") int yearTo, @RequestParam("priceFrom") int priceFrom,
                                          @RequestParam("priceTo") int priceTo) {
        List<Car> acceptedCars = carService.findByMakeYearBetweenPriceBetween(make, yearFrom, yearTo,
                priceFrom, priceTo);
        List<Advert> resultAdverts = new ArrayList<>();
        for (Car car : acceptedCars) {
            resultAdverts.addAll(car.getAdverts());
        }
        return resultAdverts;

    }


    @RequestMapping(value = "/advert/{advertId}", method = RequestMethod.DELETE)
    public void removeAdvert(@PathVariable int advertId) {
        advertService.removeAdvertById(advertId);
    }


}
