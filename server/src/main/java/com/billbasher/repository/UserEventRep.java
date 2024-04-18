package com.billbasher.repository;


import com.billbasher.model.UserEventDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEventRep extends JpaRepository<UserEventDAO, Long> {
    Optional<UserEventDAO> findByUserId_UserIdAndEventId_EventId(Long userId, Long eventId);
    List<UserEventDAO> findByUserIdUserId(Long userId);
}

