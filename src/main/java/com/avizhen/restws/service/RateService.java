package com.avizhen.restws.service;

import com.avizhen.restws.entity.Rate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Александр on 29.10.2016.
 */
@Service
public class RateService {
    private RestTemplate restTemplate;
    private Map<String, Integer> currencyAbbreviationToId;

    public RateService() {
        currencyAbbreviationToId = new HashMap<>();
        currencyAbbreviationToId.put("RUB", 298);
        currencyAbbreviationToId.put("USD", 145);
        currencyAbbreviationToId.put("EUR", 292);
        currencyAbbreviationToId.put("PLN", 293);
        restTemplate = new RestTemplate();
    }

    public Rate getRate(String currencyAbbreviation, LocalDate localDate) {
        return restTemplate.getForObject("http://www.nbrb.by/API/ExRates/Rates/" +
                currencyAbbreviationToId.get(currencyAbbreviation) +
                "?onDate=" + localDate, Rate.class);
    }

    public List<Rate> getAllRates(LocalDate localDate) {
        List<Rate> result = new ArrayList<>();
        for (Integer id : currencyAbbreviationToId.values()) {
            result.add(restTemplate.getForObject("http://www.nbrb.by/API/ExRates/Rates/" +
                    id + "?onDate=" + localDate, Rate.class));
        }
        return result;
    }

    public void addCurrencyAbbreviation(String currencyAbbreviation, int id) {
        currencyAbbreviationToId.put(currencyAbbreviation, id);
    }

}
