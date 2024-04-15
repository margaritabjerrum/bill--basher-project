package com.billbasher.services;

import com.billbasher.model.EventDAO;
import com.billbasher.model.UserDAO;
import com.billbasher.model.UserEventDAO;
import com.billbasher.repository.EventRep;
import com.billbasher.repository.UserEventRep;
import com.billbasher.repository.UserRep;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserEventService {

    private final UserEventRep userEventRep;
    private final UserRep userRep;
    private final EventRep eventRep;

    public UserEventService(UserEventRep userEventRep, UserRep userRep, EventRep eventRep) {
        this.userEventRep = userEventRep;
        this.userRep = userRep;
        this.eventRep = eventRep;
    }

    public void addUserToEvent(Long userId, Long eventId) {

        Optional<UserDAO> optionalUser = userRep.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }
        UserDAO user = optionalUser.get();


        Optional<EventDAO> optionalEvent = eventRep.findById(eventId);
        if (optionalEvent.isEmpty()) {
            throw new IllegalArgumentException("Event with ID " + eventId + " not found.");
        }
        EventDAO event = optionalEvent.get();


        UserEventDAO userEvent = new UserEventDAO();
        userEvent.setUserId(user);
        userEvent.setEventId(event);


        userEventRep.save(userEvent);
    }
}