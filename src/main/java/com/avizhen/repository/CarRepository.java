package com.avizhen.repository;

import com.avizhen.entity.Car;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Александр on 25.10.2016.
 */
@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Integer> {

}
