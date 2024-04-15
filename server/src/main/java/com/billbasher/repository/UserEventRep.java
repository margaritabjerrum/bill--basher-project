package com.billbasher.repository;


import com.billbasher.model.UserEventDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRep extends JpaRepository<UserEventDAO, Long> {

}