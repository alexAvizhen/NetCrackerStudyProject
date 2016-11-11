package com.avizhen.service;

import com.avizhen.entity.Car;
import com.avizhen.entity.CarImage;

import java.util.List;

/**
 * Created by Александр on 10.11.2016.
 */
public interface CarImageService {
    List<CarImage> getCarImagesByCar(Car car);

    CarImage getPresentationCarImageByCar(Car car);
}
