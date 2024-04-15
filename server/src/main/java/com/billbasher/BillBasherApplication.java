package com.billbasher;

import com.billbasher.dto.UserDTO;
import com.billbasher.model.UserDAO;
import com.billbasher.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Component;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BillBasherApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillBasherApplication.class, args);
    }
}

@Component
class UserRegistrationRunner implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public UserRegistrationRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        // Create a user DTO with sample data
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Naruto");
        userDTO.setSurname("Uzumaki");
        userDTO.setUsername("nrsd");
        userDTO.setPassword("Hello4432");
        userDTO.setEmail("hytdf@example.com");

        // Convert UserDTO to UserDAO
        UserDAO userDAO = new UserDAO();
        userDAO.setName(userDTO.getName());
        userDAO.setSurname(userDTO.getSurname());
        userDAO.setUsername(userDTO.getUsername());
        userDAO.setPassword(userDTO.getPassword());
        userDAO.setEmail(userDTO.getEmail());

        // Register the user
        try {
            userService.registerUser(userDAO); // Pass UserDAO instead of UserDTO
            System.out.println("User registered successfully!");
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to register user: " + e.getMessage());
        }
    }
}
