package com.avizhen.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by Александр on 19.11.2016.
 */
@Controller
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handlerResourceNotFoundException() {
        return "redirect:/404";
    }

    @RequestMapping(value = {"/404"}, method = RequestMethod.GET)
    public String notFoundPage() {
        return "404";
    }
}
