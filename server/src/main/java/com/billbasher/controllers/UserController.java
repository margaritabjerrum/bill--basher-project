package com.billbasher.controllers;

import com.billbasher.exception.UserAlreadyExistsException;
import com.billbasher.model.UserDAO;
import com.billbasher.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/api/v1/users/{id}")
  public UserDAO findUserById(@PathVariable("id") Long id) {
    return userService.findUserById(id);
  }

  @PutMapping("/api/v1/users/{id}")
  public UserDAO updateUserById(@PathVariable("id") Long id, @RequestBody UserDAO user) {
    return userService.updateUserById(id, user);
  }

  @DeleteMapping("/api/v1/users/{id}")
  public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") Long id) {
    try {
      userService.deleteUserById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/api/v1/users")
  public ResponseEntity<Iterable<UserDAO>> getAllUsers() {
    try {
      if (userService.getAllUsers().isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

