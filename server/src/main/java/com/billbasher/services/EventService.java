package com.billbasher.services;

import com.billbasher.dto.EventDTO;
import com.billbasher.model.EventDAO;
import com.billbasher.model.UserDAO;
import com.billbasher.model.UserEventDAO;
import com.billbasher.repository.EventRep;
import com.billbasher.repository.UserEventRep;
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

    @Autowired
    private UserEventService userEventService;

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private UserEventRep userEventRepository;

    public EventDAO findEventById(@PathVariable("id") Long id) {

        return eventRepository.findById(id).get();

    }

    public EventDAO updateEventById(Long id, EventDAO event) {
        EventDAO existingEvent = findEventById(id);
        if (existingEvent != null) {
            // Update the existing event with the new data
            existingEvent.setEventName(event.getEventName());

            return eventRepository.save(existingEvent);
        }
        return null;
    }

    public void deleteEventById(Long id) {
        EventDAO event = findEventById(id);
        if (event != null) {
            // Check if the event is finished
            if (!event.getEventActive()) {
                // Check if there is only one user associated with the event
                if (userEventRepository.countByEventId(event) <= 1) {
                    // Delete all expenses associated with the event
                    expenseService.deleteExpensesByEvent(event);
                    // Delete the event
                    eventRepository.delete(event);
                } else {
                    // Event cannot be deleted
                    throw new IllegalStateException("Event cannot be deleted as it is not finished or has more than one user.");
                }
            } else {
                // Event cannot be deleted
                throw new IllegalStateException("Event cannot be deleted as it is not finished or has more than one user.");
            }
        }
    }

    public List<EventDAO> getAllEvents() {

        return eventRepository.findAll();
    }

    public EventDAO createEvent(EventDAO event) {
        EventDAO createdEvent = eventRepository.save(event);

        UserEventDAO userEDAO = new UserEventDAO();
        userEDAO.setEventId(createdEvent);
        userEDAO.setUserId(event.getUserId());
        userEDAO.setTotal(0);
        userEventService.addUserToEvent(userEDAO);

        return createdEvent;
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
