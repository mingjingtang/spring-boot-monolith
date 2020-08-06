package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.models.UserProfile;
import com.example.springbootmonolith.repositories.UserProfileRepository;
import com.example.springbootmonolith.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserProfile createUserProfile(String username, UserProfile userProfile) {
        userProfileRepository.save(userProfile);
        User user = userService.getUser(username);
        user.setUserProfile(userProfile);
        return userRepository.save(user).getUserProfile();
    }

    @Override
    public UserProfile getUserProfile(String username) {
        return userProfileRepository.findProfileByUsername(username);
    }
}
