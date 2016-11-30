package com.avizhen.springmvc.controller.rest;

import com.avizhen.restws.entity.Rate;
import com.avizhen.restws.service.RateService;
import com.avizhen.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created by Александр on 08.11.2016.
 */
@RestController
@RequestMapping("/api")
public class RateRestController {
    @Autowired
    private RateService rateService;

    @RequestMapping(value = "/rate", method = RequestMethod.GET)
    @Transactional
    @JsonView(Views.Public.class)
    public List<Rate> getAllRates() {
        return rateService.getAllRates(LocalDate.now());
    }

    @RequestMapping(value = "/rate/currencies", method = RequestMethod.GET)
    @JsonView(Views.Public.class)
    public Set<String> getAllCurrencies() {
        return rateService.getAllCurrencyAbbreviation();
    }

    @RequestMapping(value = "/rate/convert", method = RequestMethod.GET)
    @JsonView(Views.Public.class)
    public double convertPriceTo(@RequestParam("price") double price, @RequestParam("abbr") String abbr) {
        double res = rateService.convertPriceTo(price, abbr);
        return BigDecimal.valueOf(res)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
