package com.billbasher.services;

import com.billbasher.model.EventDAO;
import com.billbasher.repository.EventRep;
import com.billbasher.repository.UserEventRep;

import com.billbasher.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;


@Service
public class EventService {

    @Autowired
    private EventRep eventRepository;

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private UserEventRep userEventRepository;
    @Autowired
    private UserRep userRepository;



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

        return eventRepository.save(event);
    }

}
