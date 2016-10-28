package com.avizhen.springmvc.controller;

import com.avizhen.entity.User;
import com.avizhen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Александр on 28.10.2016.
 */
@Controller
public class HelloController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/")
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }

    @RequestMapping(value = "/user/{id}")
    public String printUser(@PathVariable Integer id, ModelMap modelMap) {
        User user = userRepository.findOne(id);
        modelMap.addAttribute("user", user);
        return "user";
    }

    @RequestMapping(value = "/user")
    public ModelAndView printUsers(ModelMap modelMap) {
        ModelAndView model = new ModelAndView("users");
        model.addObject("users", userRepository.getAll());
        return model;
    }
}

