package com.avizhen.repository;

import com.avizhen.entity.Advert;
import com.avizhen.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Александр on 25.10.2016.
 */
@Repository
public interface AdvertRepository extends PagingAndSortingRepository<Advert, Integer> {
    @Query("from Advert")
    List<Advert> getAll();

}
