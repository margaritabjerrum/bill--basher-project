package com.billbasher.controllers;

import com.billbasher.dto.UserDTO;
import com.billbasher.exception.UserAlreadyExistsException;
import com.billbasher.model.UserDAO;
import com.billbasher.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/api/v1/users/{id}")
  public ResponseEntity<UserDTO> findUserById(@PathVariable("id") Long id) {
    UserDTO userDTO = userService.findUserById(id);
    return ResponseEntity.ok(userDTO);
  }


  @PutMapping("/api/v1/users/{id}")
  public UserDAO updateUserById(@PathVariable("id") Long id, @RequestBody UserDAO user) {
    return userService.updateUserById(id, user);
  }

  @DeleteMapping("/api/v1/users/{id}")
  public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") Long id) {
    userService.deleteUserById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  @GetMapping("/api/v1/users")
  public ResponseEntity<Object> getAllUsers() {
    List<UserDTO> users = userService.getAllUsers();
    if (users.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>((Object) users, HttpStatus.OK);
  }
}

