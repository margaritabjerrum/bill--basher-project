package com.billbasher.services;

import com.billbasher.dto.UserDTO;
import com.billbasher.exception.UserAlreadyExistsException;
import com.billbasher.exception.UserNotActiveException;
import com.billbasher.model.UserDAO;
import com.billbasher.pswrdhashing.AuthResponse;
import com.billbasher.pswrdhashing.JwtUtil;
import com.billbasher.pswrdhashing.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public AuthResponse login(String usernameOrEmail, String password) {
        // Retrieve user by username or email from the database
        Optional<UserDAO> userOptional = userService.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (userOptional.isPresent()) {
            UserDAO user = userOptional.get();
            // Check if user is active
            if (!user.getIsActive()) {
                throw new UserNotActiveException("User " + usernameOrEmail + " was not found, please register.");
            }
            // Verify password
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                // Generate JWT token
                String jwtToken = jwtUtil.generateToken(user);
                // Create auth response with user details and JWT token
                UserDTO userDTO = UserDTO.mapUserDAOToDTO(user);
                return new AuthResponse(userDTO, jwtToken);
            } else {
                throw new UserNotActiveException("Invalid username or password");
            }
        } else {
            throw new UserNotActiveException("User " + usernameOrEmail + " does not exist");
        }
    }



    // Function to check if JWT token is valid
    public boolean isTokenValid(String token) {
        return jwtUtil.validateToken(token);
    }

}