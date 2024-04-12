package com.billbasher.controllers;

import com.billbasher.dto.UserDTO;
import com.billbasher.model.UserDAO;
import com.billbasher.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistration {

    @Autowired
    private UserService userService;

    @PostMapping("/api/v1/register")
    public ResponseEntity<String> userRegistration(@RequestBody UserDAO userDAO) {
        try {
            userService.registerUser(userDAO);
            return ResponseEntity.ok("User registered successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
