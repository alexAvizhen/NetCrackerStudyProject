package com.avizhen.restws.service;

import com.avizhen.restws.entity.Rate;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Александр on 10.11.2016.
 */
public interface RateService {
    Rate getRate(String currencyAbbreviation, LocalDate localDate);

    List<Rate> getAllRates(LocalDate localDate);
}
