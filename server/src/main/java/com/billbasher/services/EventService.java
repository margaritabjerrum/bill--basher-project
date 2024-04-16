package com.billbasher.services;

import com.billbasher.model.EventDAO;
import com.billbasher.repository.EventRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;


@Service
public class EventService {

    @Autowired
    private EventRep eventRepository;

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
}
