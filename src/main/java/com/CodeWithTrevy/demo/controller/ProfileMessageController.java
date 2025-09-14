package com.CodeWithTrevy.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileMessageController {
    @Value("${app.message}")
    private String message;

    @GetMapping("/profile-message")
    public String getMessage(){

        return message;
    }
}
