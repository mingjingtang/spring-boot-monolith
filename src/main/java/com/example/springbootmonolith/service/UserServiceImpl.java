package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.Course;
import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.models.UserRole;
import com.example.springbootmonolith.repositories.CourseRepository;
import com.example.springbootmonolith.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Iterable<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public User signUp(User user) {
        UserRole userRole = userRoleService.getRole(user.getUserRole().getName());
        user.setUserRole(userRole);
        return userRepository.save(user);
    }

    @Override
    public User login(User user) {
        return userRepository.login(user.getUsername(), user.getPassword());
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addCourse(String username, int courseId) {
        Course course = courseRepository.findById(courseId).get();
        User user = userRepository.findByUsername(username);
        user.addCourse(course);
        return userRepository.save(user);
    }
}
