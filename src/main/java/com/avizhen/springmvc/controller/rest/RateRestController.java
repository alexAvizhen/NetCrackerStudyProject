package com.avizhen.springmvc.controller.rest;

import com.avizhen.restws.entity.Rate;
import com.avizhen.restws.service.RateService;
import com.avizhen.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

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
}
