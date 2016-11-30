package com.avizhen.restws.service.impl;

import com.avizhen.restws.entity.Rate;
import com.avizhen.restws.service.RateService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by Александр on 29.10.2016.
 */
@Service
public class RateServiceImpl implements RateService {
    private RestTemplate restTemplate;
    private Map<String, Integer> currencyAbbreviationToId;

    public RateServiceImpl() {
        currencyAbbreviationToId = new HashMap<>();
        currencyAbbreviationToId.put("RUB", 298);
        currencyAbbreviationToId.put("USD", 145);
        currencyAbbreviationToId.put("EUR", 292);
        currencyAbbreviationToId.put("PLN", 293);
        restTemplate = new RestTemplate();
    }

    @Override
    public Rate getRate(String currencyAbbreviation, LocalDate localDate) {
        return restTemplate.getForObject("http://www.nbrb.by/API/ExRates/Rates/" +
                currencyAbbreviationToId.get(currencyAbbreviation) +
                "?onDate=" + localDate, Rate.class);
    }

    @Override
    public List<Rate> getAllRates(LocalDate localDate) {
        List<Rate> result = new ArrayList<>();
        for (Integer id : currencyAbbreviationToId.values()) {
            result.add(restTemplate.getForObject("http://www.nbrb.by/API/ExRates/Rates/" +
                    id + "?onDate=" + localDate, Rate.class));
        }
        return result;
    }

    @Override
    public Set<String> getAllCurrencyAbbreviation() {
        return currencyAbbreviationToId.keySet();
    }

    @Override
    public double convertPriceTo(double price, String abbr) {
        Rate rate = getRate(abbr, LocalDate.now());
        return price / rate.getCur_OfficialRate() * rate.getCur_Scale();
    }

    public void addCurrencyAbbreviation(String currencyAbbreviation, int id) {
        currencyAbbreviationToId.put(currencyAbbreviation, id);
    }

}
