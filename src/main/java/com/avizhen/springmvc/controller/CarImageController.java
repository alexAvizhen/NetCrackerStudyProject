package com.avizhen.springmvc.controller;

import com.avizhen.entity.Car;
import com.avizhen.entity.CarImage;
import com.avizhen.service.CarImageService;
import com.avizhen.service.CarService;
import com.avizhen.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Александр on 09.11.2016.
 */

@RestController
@RequestMapping(value = "/api")
public class CarImageController {

    @Autowired
    private CarImageService carImageService;

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/car/{carId}/images", method = RequestMethod.GET)
    @Transactional
    @JsonView(Views.Public.class)
    public Set<CarImage> findAllImages(@PathVariable int carId) {
        Car car = carService.findCarById(carId);
        return carImageService.getCarImagesByCar(car);
    }

    @RequestMapping(value = "/car/{carId}/image", method = RequestMethod.GET)
    @Transactional
    @JsonView(Views.Public.class)
    public CarImage findPresentationCarImage(@PathVariable int carId) {
        Car car = carService.findCarById(carId);
        return carImageService.getPresentationCarImageByCar(car);
    }
}
