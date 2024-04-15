package com.billbasher.services;

import com.billbasher.dto.UserDTO;
import com.billbasher.exception.UserAlreadyExistsException;
import com.billbasher.pswrdhashing.PasswordEncoderUtil;
import com.billbasher.repository.UserRep;
import com.billbasher.model.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRep userRepository;
    private UserDTO mapUserDAOToDTO(UserDAO userDAO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userDAO.getUserId());
        userDTO.setName(userDAO.getName());
        userDTO.setSurname(userDAO.getSurname());
        userDTO.setUsername(userDAO.getUsername());
        userDTO.setEmail(userDAO.getEmail());
        userDTO.setUserCreated(userDAO.getUserCreated());
        return userDTO;
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    public UserDAO updateUserById(Long id, UserDAO user) {
        return userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {
        List<UserDAO> userDAOList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserDAO userDAO : userDAOList) {
            userDTOList.add(mapUserDAOToDTO(userDAO));
        }
        return userDTOList;
    }

    public UserDTO findUserById(Long id){
        Optional<UserDAO> userDAO = userRepository.findById(id);
        if (userDAO.isPresent()) {
            return mapUserDAOToDTO(userDAO.get());
        }
        throw new NoSuchElementException("User not found with id: " + id);
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
