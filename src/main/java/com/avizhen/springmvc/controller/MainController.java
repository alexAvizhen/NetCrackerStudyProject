package com.avizhen.springmvc.controller;

import com.avizhen.entity.Car;
import com.avizhen.entity.Item;
import com.avizhen.entity.Order;
import com.avizhen.entity.User;
import com.avizhen.repository.CarRepository;
import com.avizhen.repository.ItemRepository;
import com.avizhen.repository.OrderRepository;
import com.avizhen.repository.UserRepository;
import com.avizhen.rest.entity.Rate;
import com.avizhen.rest.service.RateService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Created by Александр on 28.10.2016.
 */
@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RateService rateService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value = "/")
    public String printHello(ModelMap model) {
        return "index";
    }

    @RequestMapping(value = "/user/{id}")
    public ModelAndView printUser(@PathVariable Integer id) {
        ModelAndView model = new ModelAndView("user");
        User user = userRepository.findOne(id);
        model.addObject("users", Arrays.asList(user));
        return model;
    }

    @RequestMapping(value = "/user")
    public ModelAndView printUsers(ModelMap modelMap) {
        ModelAndView model = new ModelAndView("user");
        model.addObject("users", userRepository.getAll());
        return model;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    @Transactional
    public ModelAndView printUserOrders(@RequestParam("userId") Integer userId, ModelMap modelMap) {
        ModelAndView model = new ModelAndView("order");
        User user = userRepository.findOne(userId);
        model.addObject("user", user);
        Hibernate.initialize(user.getOrders());
        model.addObject("orders", user.getOrders());
        //model.addObject("message", user.getOrders().size() + "   " + user.toString());
        return model;
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    @Transactional
    public ModelAndView printOrderItems(@RequestParam("orderId") Integer orderId, ModelMap modelMap) {
        ModelAndView model = new ModelAndView("item");
        Order order = orderRepository.findOne(orderId);
        model.addObject("order", order);
        Hibernate.initialize(order.getItems());
        model.addObject("items", order.getItems());
        //model.addObject("message", order.getItems().size() + "   " +
          //      order.getItems().iterator().next().toString());
        return model;
    }

    @RequestMapping(value = "/itemcar", method = RequestMethod.GET)
    public ModelAndView printItemCar(@RequestParam("itemId") Integer itemId, ModelMap modelMap) {
        ModelAndView model = new ModelAndView("car");
        Item item = itemRepository.findOne(itemId);
        Car car = item.getCar();
        model.addObject("cars", Arrays.asList(car));
        //model.addObject("message", car.toString());
        return model;
    }

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public ModelAndView printAllCar(ModelMap modelMap) {
        ModelAndView model = new ModelAndView("car");
        model.addObject("cars", carRepository.findAll());
        //model.addObject("message", car.toString());
        return model;
    }




    @RequestMapping(value = "/rate", method = RequestMethod.GET)
    public String printRate(@RequestParam("currencyAbbreviation") String currencyAbbreviation,
                            ModelMap modelMap) {
        Rate rate = rateService.getRate(currencyAbbreviation, LocalDate.now());
        modelMap.addAttribute("rate", rate);
        modelMap.addAttribute("message", rate.toString());
        return "rate";
    }


}

