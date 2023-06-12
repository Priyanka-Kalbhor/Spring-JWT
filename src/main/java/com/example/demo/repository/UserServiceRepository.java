package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserServiceRepository {


    User createUser(User user);

    List<User> getAllUsers();
}
