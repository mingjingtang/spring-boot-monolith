package com.example.springbootmonolith.controller;

import com.example.springbootmonolith.models.JwtResponse;
import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World!!";
    }

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("user/list")
    public Iterable<User> listUsers(){
        return userService.listUser();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user){
        return ResponseEntity.ok(new JwtResponse(userService.signUp(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
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
