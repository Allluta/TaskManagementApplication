package com.example.taskmanagement.controllers;

import com.example.taskmanagement.entities.User;
import com.example.taskmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user",new User());
        return "signup_form";
    }
    @PostMapping("/process_register")
    public String processRegistration(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return "register_success";
    }
    @GetMapping("/list_users")
    public String viewUsersList() {
        return "users";
    }
}
