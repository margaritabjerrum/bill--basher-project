package com.billbasher.services;

import com.billbasher.dto.UserDTO;
import com.billbasher.exception.UserAlreadyExistsException;
import com.billbasher.pswrdhashing.PasswordEncoderUtil;
import com.billbasher.repository.UserRep;
import com.billbasher.model.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRep userRepository;
    public void deleteUserById(Long id) {

        userRepository.deleteById(id);
    }
    public UserDAO updateUserById(Long id, UserDAO user) {
        return userRepository.save(user);
    }

    public List<UserDAO> getAllUsers() {
        return userRepository.findAll();
    }
    public UserDAO findUserById(@PathVariable("id") Long id){
        return userRepository.findById(id).get();
    }
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
