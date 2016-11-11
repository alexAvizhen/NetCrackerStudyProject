package com.avizhen.service.impl;

import com.avizhen.entity.Advert;
import com.avizhen.repository.AdvertRepository;
import com.avizhen.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Александр on 07.11.2016.
 */
@Service
@Transactional
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    private AdvertRepository advertRepository;

    @Override
    public List<Advert> findAllAdverts() {
        List<Advert> result = advertRepository.getAll();
        return result;
    }

    @Override
    public Advert findAdvertById(int advertId) {
        return advertRepository.findOne(advertId);
    }

    @Override
    public Advert addAdvert(Advert advert) {
        return advertRepository.save(advert);
    }

    @Override
    public void removeAdvertById(int advertId) {
        advertRepository.delete(advertId);
    }
}
