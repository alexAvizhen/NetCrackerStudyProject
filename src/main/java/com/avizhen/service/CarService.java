package com.avizhen.service;

import com.avizhen.entity.Car;
import com.avizhen.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Александр on 09.11.2016.
 */
@Service
@Transactional
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> findAllCars() {
        List<Car> result = carRepository.getAll();
        return result;
    }

    public Car findCarById(int carId) {
        return carRepository.findOne(carId);
    }
}
