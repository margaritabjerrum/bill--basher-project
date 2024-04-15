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
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Surname is required")
    @Size(min = 2, max = 20, message = "Surname must be between 2 and 50 characters")
    private String surname;

    @NotBlank(message = "Username is required")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
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
