package com.example.springbootmonolith.repositories;

import com.example.springbootmonolith.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {

    @Query("FROM User u WHERE u.username = ?1 and u.password = ?2 ")
    public User login(String username, String password);

    public User findByUsername(String username);


    @Override
    public List<User> findAll();

}
