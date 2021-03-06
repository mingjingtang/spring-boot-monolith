package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.UserRole;
import com.example.springbootmonolith.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRole createRole(UserRole newRole) {
        return userRoleRepository.save(newRole);
    }

    @Override
    public UserRole getRole(String rolename) {
        return userRoleRepository.findByName(rolename);
    }
}
