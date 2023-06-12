package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceRepositoryImpl implements UserServiceRepository {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("Password: "+user.getPassword());
        User newUser= userRepository.save(user);
        return newUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
