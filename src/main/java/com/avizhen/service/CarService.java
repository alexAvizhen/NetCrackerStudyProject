package com.avizhen.service;

import com.avizhen.entity.Car;

import java.util.List;
import java.util.Set;

/**
 * Created by Александр on 10.11.2016.
 */
public interface CarService {
    List<Car> findAllCars();

    Car findCarById(int carId);

    Set<String> findAllMakes();

    List<Car> findByMakeYearBetweenPriceBetween(String make, int yearFrom, int yearTo,
                                                int priceFrom, int priceTo);

}
