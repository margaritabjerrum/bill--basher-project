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

    public UserDAO registerUser(UserDAO userDAO) {
        try {
            userDAO.setPassword(PasswordEncoderUtil.encodePassword(userDAO.getPassword()));
            UserDAO existingUser = userRepository.findByUsernameOrEmail(userDAO.getUsername(), userDAO.getEmail());
            if (existingUser != null) {
                throw new UserAlreadyExistsException("Username or email already exists");
            }

            return userRepository.save(userDAO);

        }
        catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
