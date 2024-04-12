package com.billbasher.repository;

import com.billbasher.model.EventDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRep extends JpaRepository<EventDAO, Long> {

}
