package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserInfoUserDetailsImpl;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

//    private String final UserInfoUserDetailsImpl userInfoUserDetails;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserInfoUserDetailsImpl userInfoUserDetails) {
//        this.userInfoUserDetails = userInfoUserDetails;
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = userRepository.findByUsername(username);


        return userInfo.map(UserInfoUserDetailsImpl::new)
                .orElseThrow(() -> new RuntimeException("user not found " + username));
    }
}
