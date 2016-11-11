package com.avizhen.service.impl;

import com.avizhen.entity.Car;
import com.avizhen.entity.CarImage;
import com.avizhen.repository.CarImageRepository;
import com.avizhen.service.CarImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Александр on 09.11.2016.
 */
@Service
@Transactional
public class CarImageServiceImpl implements CarImageService {

    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private CarImageRepository carImageRepository;

    @Override
    public List<CarImage> getCarImagesByCar(Car car) {
        return carImageRepository.findByCar(car);
    }

    @Override
    public CarImage getPresentationCarImageByCar(Car car) {
        List<CarImage> images = carImageRepository.findByCar(car, new PageRequest(0, 1));
        if (images.iterator().hasNext()) {
            return images.iterator().next();
        }
        return null;
    }


}
