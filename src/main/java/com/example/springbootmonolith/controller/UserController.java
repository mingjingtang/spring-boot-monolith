package com.example.springbootmonolith.controller;

import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("user/list")
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

    @DeleteMapping("user/{userId}")
    public void delete(@PathVariable Long userId){
        userService.deleteById(userId);
    }

    @PutMapping("user/{username}/{courseId}")
    public User addCourse(@PathVariable String username, @PathVariable int courseId){
        return userService.addCourse(username, courseId);
    }
}
