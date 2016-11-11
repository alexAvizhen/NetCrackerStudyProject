package com.avizhen.repository;

import com.avizhen.entity.Car;
import com.avizhen.entity.CarImage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Александр on 25.10.2016.
 */
@Repository
public interface CarImageRepository extends PagingAndSortingRepository<CarImage, Integer> {

    List<CarImage> findByCar(Car car, Pageable pageable);

    List<CarImage> findByCar(Car car);
}
