package com.billbasher.dto;

import com.billbasher.model.UserDAO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String name;
    private String surname;
    private String username;
    private String email;
    private LocalDateTime userCreated = LocalDateTime.now();

    public static UserDTO mapUserDAOToDTO(UserDAO userDAO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userDAO.getUserId());
        userDTO.setName(userDAO.getName());
        userDTO.setSurname(userDAO.getSurname());
        userDTO.setUsername(userDAO.getUsername());
        userDTO.setEmail(userDAO.getEmail());
        userDTO.setUserCreated(userDAO.getUserCreated());
        return userDTO;
    }
}
