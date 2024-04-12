package com.billbasher.services;

import com.billbasher.dto.UserDTO;
import com.billbasher.repository.UserRep;
import com.billbasher.model.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRep userRepository;

    public void registerUser(UserDAO userDAO) {
        UserDAO existingUser = userRepository.findByUsernameOrEmail(userDAO.getUsername(), userDAO.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("Username or email already exists");
        }

        userRepository.save(userDAO);
    }
}
