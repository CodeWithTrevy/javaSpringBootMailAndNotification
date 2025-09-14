package com.CodeWithTrevy.demo.controller;

import com.CodeWithTrevy.demo.model.Users;
import com.CodeWithTrevy.demo.services.EmailService;
import com.CodeWithTrevy.demo.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Controller
public class SignupController {

    private final UserServices userServices;
    private final EmailService emailService;

    @Autowired
    public SignupController(UserServices userServices, EmailService emailService) {
        this.userServices = userServices;
        this.emailService = emailService;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("signupRequest", new Users());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(
            @ModelAttribute("signupRequest") @Valid Users users,
            Model model
    ) {
        try {
            users.setCreatedAt(LocalDateTime.now());
            userServices.addUser(users);


            emailService.sendWelcomeEmail(users.getEmail(), users.getUsername());

            model.addAttribute("successMessage", "User registered successfully! A welcome email has been sent.");
            model.addAttribute("signupRequest", new Users()); // reset form
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "signup";
    }

}
