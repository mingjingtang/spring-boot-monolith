package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    public List<User> listUser();
    public String signUp(User user);
    public String login(User user);
    public void deleteById(Long userId);
    public User getUser(String username);
    public User addCourse(String username, int courseId);
}
