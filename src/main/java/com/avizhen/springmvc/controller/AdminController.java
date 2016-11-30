package com.avizhen.springmvc.controller;

import com.avizhen.entity.Advert;
import com.avizhen.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Александр on 22.11.2016.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdvertService advertService;

    @RequestMapping(value = "/advert/delete", method = RequestMethod.POST)
    public ModelAndView removeAdvert(@RequestParam("deleteAdvertId") int deleteAdvertId) {
        advertService.removeAdvertById(deleteAdvertId);
        ModelAndView modelAndView = new ModelAndView("redirect:/advert");
        modelAndView.addObject("msg", "Advert was deleted successful");
        return modelAndView;
    }

    @RequestMapping(value = "/advert/edit", method = RequestMethod.POST)
    public ModelAndView editAdvert(@RequestParam("editAdvertId") int editAdvertId) {
        ModelAndView modelAndView = new ModelAndView("editAdvert");
        Advert advert = advertService.findAdvertById(editAdvertId);
        if (advert == null) {
            return modelAndView;
        }
        modelAndView.addObject("advertId", advert.getId());
        modelAndView.addObject("carId", advert.getCar().getId());
        return modelAndView;
    }
    @RequestMapping(value = "/advert/add", method = RequestMethod.POST)
    public String addAdvert() {
        return "editAdvert";
    }
}
