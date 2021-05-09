package com.springboot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/greeting")
    public String welcome() {
        return "Greeting to Spring boot Security , you have validated the token based authentication !!";
    }

}
