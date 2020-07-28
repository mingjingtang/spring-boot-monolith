package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> listUser() {
        return userRepository.findAll();
    }
}
