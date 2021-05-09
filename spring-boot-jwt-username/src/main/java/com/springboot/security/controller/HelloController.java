package com.springboot.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public String welcome() {
        return "Hello to Spring boot Security , you have validated the token based authentication !!";
    }

}
