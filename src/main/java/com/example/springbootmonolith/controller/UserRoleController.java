package com.example.springbootmonolith.controller;

import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.models.UserRole;
import com.example.springbootmonolith.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

    @PostMapping
    public UserRole createRole(@RequestBody UserRole role){
        return userRoleService.createRole(role);
    }

    @GetMapping("/{rolename}")
    public UserRole getRole(@PathVariable String rolename){
        return userRoleService.getRole(rolename);
    }
}
