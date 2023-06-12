package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserAuthenticationDetailsDTO;
import com.example.demo.model.UserAuthenticationResponse;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserServiceRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jwt/users")
public class UserController {


    @Autowired
    private UserServiceRepositoryImpl userServiceRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody User userInfo) {
        return ResponseEntity.ok().body(userServiceRepository.createUser(userInfo));
    }

    @PostMapping("/login")
    public UserAuthenticationResponse authenticateAndGetToken(@RequestBody UserAuthenticationDetailsDTO authRequest) {
        System.out.println("UserAuthenticationDetailsDTO = " + authRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            UserAuthenticationResponse userAuthResponse = new UserAuthenticationResponse();
            userAuthResponse.setToken(jwtService.generateToken(authRequest.getUsername()));
            userAuthResponse.setMessage("User is validated successfully..!!");
            System.out.println("User auth response-->>"+userAuthResponse);
            return userAuthResponse;
        } else {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
    }

    @GetMapping("/get/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userServiceRepository.getAllUsers();
        return ResponseEntity.ok(users);
    }


}
