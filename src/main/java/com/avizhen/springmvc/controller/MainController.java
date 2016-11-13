package com.avizhen.springmvc.controller;

import com.avizhen.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Александр on 28.10.2016.
 */
@Controller
public class MainController {
    @Autowired
    private CarService carService;


    @RequestMapping(value = "/")
    public String printHello(ModelMap model) {
        return "index";
    }


    @RequestMapping(value = "/advert", method = RequestMethod.GET)
    public String getAdverts() {
        return "adverts";
    }

    @RequestMapping(value = "/advert/{id}", method = RequestMethod.GET)
    public ModelAndView  getAdvert(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("advert");
        modelAndView.addObject("advertId", id);
        return modelAndView;
    }


}

