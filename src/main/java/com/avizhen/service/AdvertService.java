package com.avizhen.service;

import com.avizhen.entity.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Александр on 10.11.2016.
 */
public interface AdvertService {
    List<Advert> findAllAdverts();

    Advert findAdvertById(int advertId);

    Advert addAdvert(Advert advert);

    void removeAdvertById(int advertId);

    Page<Advert> findPaginated(Pageable pageable);


    long getCount();
}
