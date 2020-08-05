package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public Iterable<User> listUser();
    public User signUp(User user);
    public User login(User user);
    public void deleteById(Long userId);
    public User getUser(String username);
    public User addCourse(String username, int courseId);
}
