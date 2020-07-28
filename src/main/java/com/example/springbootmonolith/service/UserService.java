package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public Iterable<User> listUser();
}
