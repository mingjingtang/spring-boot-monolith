package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.UserProfile;

public interface UserProfileService {
    public UserProfile createUserProfile(String username, UserProfile userProfile);
    public UserProfile getUserProfile(String username);
}
