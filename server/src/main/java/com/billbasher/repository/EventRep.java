package com.billbasher.repository;

import com.billbasher.model.EventDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRep extends JpaRepository<EventDAO, Long> {

}
