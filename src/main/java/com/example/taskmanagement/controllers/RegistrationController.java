package com.example.taskmanagement.controllers;

import com.example.taskmanagement.entities.User;
import com.example.taskmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        System.out.println("Register endpoint hit!"); // Log do konsoli
        // Logi dla parametrów użytkownika
        System.out.println("User email: " + user.getEmail());
        System.out.println("User password: " + user.getPassword());
        System.out.println("User first name: " + user.getFirstName());
        System.out.println("User last name: " + user.getLastName());

        // Kodowanie hasła
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Zapisywanie użytkownika w bazie danych
        userRepository.save(user);

        // Zwracanie odpowiedzi
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
