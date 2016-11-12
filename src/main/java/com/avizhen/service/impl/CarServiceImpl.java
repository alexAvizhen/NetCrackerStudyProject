package com.avizhen.service.impl;

import com.avizhen.entity.Car;
import com.avizhen.repository.CarRepository;
import com.avizhen.service.CarService;
import com.avizhen.web.model.JsonPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Александр on 09.11.2016.
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAllCars() {
        List<Car> result = carRepository.getAll();
        return result;
    }

    @Override
    public Car findCarById(int carId) {
        return carRepository.findOne(carId);
    }

    @Override
    public Set<String> findAllMakes() {
        Set<String> result = new TreeSet<>();
        for (Car car : carRepository.findAll()) {
            result.add(car.getMake());
        }
        return result;
    }

    @Override
    public List<Car> findByMakeYearBetweenPriceBetween(String make, int yearFrom, int yearTo,
                                                       int priceFrom, int priceTo) {
        if (yearFrom == -1) {
            yearFrom= findTheLimitCarByDate(Sort.Direction.ASC).getYear();
        }
        if (yearTo == -1) {
            yearTo = findTheLimitCarByDate(Sort.Direction.DESC).getYear();
        }
        if (priceFrom == -1) {
            priceFrom = findTheLimitCarByPrice(Sort.Direction.ASC).getPrice();
        }
        if (priceTo == -1) {
            priceTo = findTheLimitCarByPrice(Sort.Direction.DESC).getPrice();
        }
        if (make.isEmpty()) {
            List<Car> result = new ArrayList<>();
            for (String m : findAllMakes()) {
                result.addAll(carRepository.findByMakeAndPriceBetweenAndYearBetween(m, priceFrom, priceTo,
                        yearFrom, yearTo));
            }
            return result;
        }
        return carRepository.findByMakeAndPriceBetweenAndYearBetween(make, priceFrom, priceTo,
                yearFrom, yearTo);
    }

    @Override
    public Page<Car> findByMakeYearBetweenPriceBetween(String make, int yearFrom, int yearTo, int priceFrom, int priceTo,
                                                       Pageable pageable) {
        if (yearFrom == -1) {
            yearFrom= findTheLimitCarByDate(Sort.Direction.ASC).getYear();
        }
        if (yearTo == -1) {
            yearTo = findTheLimitCarByDate(Sort.Direction.DESC).getYear();
        }
        if (priceFrom == -1) {
            priceFrom = findTheLimitCarByPrice(Sort.Direction.ASC).getPrice();
        }
        if (priceTo == -1) {
            priceTo = findTheLimitCarByPrice(Sort.Direction.DESC).getPrice();
        }
        if (make.isEmpty()) {
            Page<Car> page = carRepository.findByPriceBetweenAndYearBetween(priceFrom, priceTo, yearFrom, yearTo, pageable);
            return new JsonPageResponse<Car>(page, pageable);
        }
        return carRepository.findByMakeAndPriceBetweenAndYearBetween(make, priceFrom, priceTo, yearFrom, yearTo, pageable);
    }

    public Car findTheLimitCarByPrice(Sort.Direction sortDirection) {
        Page<Car> carsPage = carRepository.findAll(new PageRequest(0, 1, sortDirection, "price"));
        if (carsPage.iterator().hasNext()) {
            return carsPage.iterator().next();
        } else {
            return null;
        }
    }

    public Car findTheLimitCarByDate(Sort.Direction sortDirection) {
        Page<Car> carsPage = carRepository.findAll(new PageRequest(0, 1, sortDirection, "year"));
        if (carsPage.iterator().hasNext()) {
            return carsPage.iterator().next();
        } else {
            return null;
        }
    }
}
