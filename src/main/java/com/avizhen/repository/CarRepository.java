package com.avizhen.repository;

import com.avizhen.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Александр on 25.10.2016.
 */
@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Integer> {
    @Query("from Car")
    List<Car> getAll();

    List<Car> findByMakeAndPriceBetweenAndYearBetween(String make, int price1, int price2,
                                                      int year1, int year2);

    Page<Car> findByMakeAndPriceBetweenAndYearBetween(String make, int price1, int price2,
                                                      int year1, int year2, Pageable pageable);

    Page<Car> findByPriceBetweenAndYearBetween(int price, int price2, int year1, int year2,
                                               Pageable pageable);

    //Car findTopOrderByPriceDesc();

}
