package com.avizhen.service;

import com.avizhen.entity.Car;
import com.avizhen.entity.CarImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Александр on 09.11.2016.
 */
@Service
@Transactional
public class CarImageService {

    private static final Logger LOG = LogManager.getLogger();

    public Set<CarImage> getCarImagesByCar(Car car) {
        Set<CarImage> images = new HashSet<>(car.getImages());
        LOG.info(images);
        return images;
    }

    public CarImage getPresentationCarImageByCar(Car car) {
        Set<CarImage> images = car.getImages();
        if (images.iterator().hasNext()) {
            return images.iterator().next();
        }
        return null;
    }


}
