package com.billbasher.services;

import com.billbasher.dto.UserDTO;
import com.billbasher.exception.UserNotActiveException;
import com.billbasher.model.UserDAO;
import com.billbasher.pswrdhashing.AuthResponse;
import com.billbasher.pswrdhashing.JwtUtil;
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
                throw new UserNotActiveException("User is not active");
            }
            // Verify password
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                // Generate JWT token
                String jwtToken = jwtUtil.generateToken(user);
                // Create auth response with user details and JWT token
                UserDTO userDTO = UserDTO.mapUserDAOToDTO(user);
                return new AuthResponse(userDTO, jwtToken);
            }
        }
        return null; // Return null if user doesn't exist or credentials are incorrect
    }
}
