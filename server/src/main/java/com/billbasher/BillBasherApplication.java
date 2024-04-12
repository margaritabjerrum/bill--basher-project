package com.billbasher;

import com.billbasher.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//TEST FILE TO CHECK USERS
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })

public class BillBasherApplication{

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BillBasherApplication.class, args);
    }

//    @Override
//    public void run(String... args) {
//        // Create a user DTO with sample data
//        UserDTO userDTO = new UserDTO();
//        userDTO.setName("Renats");
//        userDTO.setSurname("Junusovs");
//        userDTO.setUsername("gnarlyken");
//        userDTO.setPassword("Hello4432");
//        userDTO.setEmail("renat@example.com");
//
//        // Register the user
//        try {
//            userService.registerUser(userDTO);
//            System.out.println("User registered successfully!");
//        } catch (IllegalArgumentException e) {
//            System.err.println("Failed to register user: " + e.getMessage());
//        }
//    }
}
