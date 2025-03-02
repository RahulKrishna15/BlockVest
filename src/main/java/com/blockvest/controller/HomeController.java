package com.blockvest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home()
    {
        return "Welcome to my trading platform";
    }

    @GetMapping("/xxx")
    public String secure()
    {
        return "Welcome to my platform";
    }
}



