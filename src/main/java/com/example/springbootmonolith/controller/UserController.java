package com.example.springbootmonolith.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World!!";
    }
}
