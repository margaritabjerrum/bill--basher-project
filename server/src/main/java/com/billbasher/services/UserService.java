package com.billbasher.services;

import com.billbasher.dto.UserDTO;
import com.billbasher.exception.UserAlreadyExistsException;
import com.billbasher.pswrdhashing.PasswordEncoderUtil;
import com.billbasher.repository.UserRep;
import com.billbasher.model.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRep userRepository;

    public void registerUser(UserDAO userDAO) {
        try {
            userDAO.setPassword(PasswordEncoderUtil.encodePassword(userDAO.getPassword()));
            UserDAO existingUser = userRepository.findByUsernameOrEmail(userDAO.getUsername(), userDAO.getEmail());
            if (existingUser != null) {
                throw new RuntimeException("Username or email already exists");
            }

            userRepository.save(userDAO);

        } catch (UserAlreadyExistsException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create user", e);
        }
    }
}
