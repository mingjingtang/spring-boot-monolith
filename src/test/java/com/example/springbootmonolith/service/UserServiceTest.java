package com.example.springbootmonolith.service;

import com.example.springbootmonolith.config.JwtUtil;
import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.models.UserRole;
import com.example.springbootmonolith.repositories.CourseRepository;
import com.example.springbootmonolith.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    private UserRoleService userRoleService;

    @Mock
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private User user;

    @InjectMocks
    private UserRole userRole;


    @Before
    public void initializeDummyUser(){
        user.setUsername("batman");
        user.setPassword("robin");

        userRole.setName("ROLE_DBA");

        user.setUserRole(userRole);
        user.setPassword("12345");
    }

    @Test
    public void getUser_ReturnsUser_Success(){

        when(userRepository.findByUsername(anyString())).thenReturn(user);

        User tempUser = userService.getUser(user.getUsername());

        assertEquals(tempUser.getUsername(), user.getUsername());
    }

    @Test
    public void login_UserNotFound_Error(){

        when(userRepository.findByUsername(anyString())).thenReturn(null);

        String token = userService.login(user);

        assertEquals(token, null);
    }

    @Test
    public void signup_ReturnsUser_Success(){

        when(userRoleService.getRole(user.getUserRole().getName())).thenReturn(userRole);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("12345");
        when(userRepository.save(user)).thenReturn(user);

//        String returnString = userService.signUp(user);
//        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
//
//        assertEquals(returnString, jwtUtil.generateToken(userDetails));
    }

    @Test
    public void signup_ReturnNull_Success(){

        when(userRoleService.getRole(user.getUserRole().getName())).thenReturn(userRole);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("12345");
        when(userRepository.save(user)).thenReturn(null);

        String token = userService.signUp(user);

        assertEquals(token, null);
    }
}
