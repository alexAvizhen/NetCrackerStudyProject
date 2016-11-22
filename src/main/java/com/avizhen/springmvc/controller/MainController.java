package com.avizhen.springmvc.controller;

import com.avizhen.entity.Advert;
import com.avizhen.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Александр on 28.10.2016.
 */

@Controller
public class MainController {
    @Autowired
    private AdvertService advertService;


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

    @RequestMapping(value = "/admin/advert/delete", method = RequestMethod.POST)
    public ModelAndView removeAdvert(@RequestParam("deleteAdvertId") int deleteAdvertId) {
        advertService.removeAdvertById(deleteAdvertId);
        ModelAndView modelAndView = new ModelAndView("adverts");
        modelAndView.addObject("msg", "Advert was deleted successful");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/advert/edit", method = RequestMethod.POST)
    public ModelAndView editAdvert(@RequestParam("editAdvertId") int editAdvertId) {
        Advert advert = advertService.findAdvertById(editAdvertId);
        ModelAndView modelAndView = new ModelAndView("editAdvert");
        modelAndView.addObject("advertId", advert.getId());
        modelAndView.addObject("carId", advert.getCar().getId());
        return modelAndView;
    }
    @RequestMapping(value = "/admin/advert/add", method = RequestMethod.POST)
    public ModelAndView addAdvert() {
        return new ModelAndView("editAdvert");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }


    @RequestMapping("/favicon.ico")
    String favicon() {
        return "forward:/resources/images/favicon.ico";
    }
}

