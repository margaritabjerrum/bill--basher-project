package com.billbasher.repository;

import com.billbasher.model.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRep extends JpaRepository<UserDAO, Long> {

    Optional<UserDAO> findByUsername(String username);

    Optional<UserDAO> findByEmail(String email);

    Optional<UserDAO> findByUsernameOrEmail(String username, String email);
}
