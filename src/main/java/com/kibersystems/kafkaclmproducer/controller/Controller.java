package com.kibersystems.kafkaclmproducer.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String displayHome() {
        return "home";
    }

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }


}
