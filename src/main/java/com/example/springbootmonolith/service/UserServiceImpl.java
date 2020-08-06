package com.example.springbootmonolith.service;

import com.example.springbootmonolith.config.JwtUtil;
import com.example.springbootmonolith.models.Course;
import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.models.UserRole;
import com.example.springbootmonolith.repositories.CourseRepository;
import com.example.springbootmonolith.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public Iterable<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public String signUp(User user) {
        UserRole userRole = userRoleService.getRole(user.getUserRole().getName());
        user.setUserRole(userRole);

        User savedUser = userRepository.save(user);
        if(savedUser.getUsername() != null && savedUser.getPassword() != null){
            UserDetails userDetails= loadUserByUsername(savedUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        else{
            return null;
        }
    }

    @Override
    public String login(User user) {
        if(userRepository.login(user.getUsername(), user.getPassword()) != null){
            UserDetails userDetails = loadUserByUsername(user.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);

        if(user==null)
            throw new UsernameNotFoundException("User null");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));

        return authorities;
    }
}
