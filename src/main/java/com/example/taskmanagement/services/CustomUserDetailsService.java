package com.example.taskmanagement.services;

import com.example.taskmanagement.config.CustomUserDetails;
import com.example.taskmanagement.entities.User;
import com.example.taskmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
 if(user == null) {
     throw new UsernameNotFoundException("User not found");
 }
        return new CustomUserDetails(user);
    }
}
