package com.avizhen.springmvc.controller;

import com.avizhen.service.CarService;
import com.avizhen.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Александр on 10.11.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/car/makes", method = RequestMethod.GET)
    @JsonView(Views.Public.class)
    public Set<String> findAllMakes() {
        return carService.findAllMakes();
    }

}
