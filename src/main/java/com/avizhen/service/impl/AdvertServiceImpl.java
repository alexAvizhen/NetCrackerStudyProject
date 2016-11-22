package com.avizhen.service.impl;

import com.avizhen.entity.Advert;
import com.avizhen.repository.AdvertRepository;
import com.avizhen.repository.CarRepository;
import com.avizhen.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private CarRepository carRepository;

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
        carRepository.save(advert.getCar());
        return advertRepository.save(advert);
    }

    @Override
    public void removeAdvertById(int advertId) {
        advertRepository.delete(advertId);
    }

    @Override
    public Page<Advert> findPaginated(Pageable pageable) {
        return advertRepository.findAll(pageable);
    }

    @Override
    public long getCount() {
        return advertRepository.count();
    }
}
