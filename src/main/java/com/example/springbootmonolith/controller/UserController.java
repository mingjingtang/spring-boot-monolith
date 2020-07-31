package com.example.springbootmonolith.controller;

import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/hello")
//    public String helloWorld(){
//        return "Hello World!!";
//    }

    @GetMapping("/list")
    public Iterable<User> listUsers(){
        return userService.listUser();
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody User user){
        return userService.signUp(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user){
        return userService.login(user);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId){
        userService.deleteById(userId);
    }
}
