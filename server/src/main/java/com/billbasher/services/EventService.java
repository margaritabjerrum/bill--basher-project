package com.billbasher.services;

import com.billbasher.dto.EventDTO;
import com.billbasher.model.EventDAO;
import com.billbasher.model.UserDAO;
import com.billbasher.repository.EventRep;
import com.billbasher.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EventService {

    @Autowired
    private EventRep eventRepository;
    @Autowired
    private UserRep userRepository;
    public EventDAO findEventById(@PathVariable("id") Long id) {

        return eventRepository.findById(id).get();

    }

    public EventDAO updateEventById(Long id, EventDAO event) {

        return eventRepository.save(event);
    }

    public void deleteEventById(Long id) {

        eventRepository.deleteById(id);
    }

    public List<EventDAO> getAllEvents() {

        return eventRepository.findAll();
    }

    public EventDAO createEvent(EventDAO event) {

        return eventRepository.save(event);
    }
    public List<EventDTO> getEventsByUserId(Long userId) {
        Optional<UserDAO> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }
        UserDAO user = userOptional.get();

        List<EventDAO> events = eventRepository.findByUserId(user);

        return events.stream()
                .map(event -> new EventDTO(event.getEventId(), event.getEventName()))
                .collect(Collectors.toList());
    }
}
