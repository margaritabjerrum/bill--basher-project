package com.billbasher.controllers;

import com.billbasher.exception.UserAlreadyExistsException;
import com.billbasher.model.UserDAO;
import com.billbasher.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/api/v1/register")
  public ResponseEntity<String> userRegistration(@RequestBody UserDAO userDAO) {
    try {
      userService.registerUser(userDAO);
      return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    } catch (UserAlreadyExistsException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    } catch (Exception e) {
      return new ResponseEntity<>("Failed to register user", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

