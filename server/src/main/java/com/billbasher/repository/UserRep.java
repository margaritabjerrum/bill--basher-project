package com.billbasher.repository;

import com.billbasher.model.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends JpaRepository<UserDAO, Long> {
    UserDAO findByUsernameOrEmail(String username, String email);
}
