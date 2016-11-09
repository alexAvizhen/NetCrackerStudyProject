package com.avizhen.service;

import com.avizhen.entity.Advert;
import com.avizhen.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Александр on 07.11.2016.
 */
@Service
@Transactional
public class AdvertService {

    @Autowired
    private AdvertRepository advertRepository;

    public List<Advert> findAllAdverts() {
        List<Advert> result = advertRepository.getAll();
        return result;
    }

    public Advert findAdvertById(int advertId) {
        return advertRepository.findOne(advertId);
    }

    public Advert addAdvert(Advert advert) {
        return advertRepository.save(advert);
    }

    public void removeAdvertById(int advertId) {
        advertRepository.delete(advertId);
    }
}
