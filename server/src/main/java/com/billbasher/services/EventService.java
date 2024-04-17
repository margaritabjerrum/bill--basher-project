package com.billbasher.services;

import com.billbasher.model.EventDAO;
import com.billbasher.repository.EventRep;
import com.billbasher.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRep eventRepository;

    @Autowired
    private UserRep userRepository;

    public EventDAO findEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public EventDAO updateEventById(Long id, EventDAO event) {
        event.setEventId(id);
        return eventRepository.save(event);
    }

    public void deleteEventById(Long id) {
        EventDAO event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));

        // Check if the event is finished or there's only the creator user in it
        if (!event.getEventActive() || event.getExpenses().isEmpty()) {
            eventRepository.delete(event);
        } else {
            throw new RuntimeException("Cannot delete event. It is not finished or there are other users in it.");
        }
    }

    public List<EventDAO> getAllEvents() {
        return eventRepository.findAll();
    }

    public EventDAO createEvent(EventDAO event) {
        return eventRepository.save(event);
    }


}