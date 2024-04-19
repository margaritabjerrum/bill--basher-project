package com.billbasher;

import com.billbasher.dto.EventDTO;
import com.billbasher.model.EventDAO;
import com.billbasher.model.UserDAO;
import com.billbasher.model.UserEventDAO;
import com.billbasher.repository.EventRep;
import com.billbasher.repository.UserEventRep;
import com.billbasher.repository.UserRep;
import com.billbasher.services.EventService;
import com.billbasher.services.ExpenseService;
import com.billbasher.services.UserEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @Mock
    private EventRep eventRepository;

    @Mock
    private UserRep userRepository;

    @Mock
    private UserEventRep userEventRepository;

    @Mock
    private UserEventService userEventService;

    @Mock
    private ExpenseService expenseService;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindEventById() {
        Long eventId = 1L;
        EventDAO event = new EventDAO();
        event.setEventId(eventId);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        EventDAO foundEvent = eventService.findEventById(eventId);

        assertEquals(event, foundEvent);
    }

    @Test
    void testUpdateEventById() {
        Long eventId = 1L;
        EventDAO existingEvent = new EventDAO();
        existingEvent.setEventId(eventId);
        existingEvent.setEventName("Old Event Name");
        EventDAO updatedEvent = new EventDAO();
        updatedEvent.setEventName("New Event Name");
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));
        when(eventRepository.save(existingEvent)).thenReturn(updatedEvent);

        EventDAO result = eventService.updateEventById(eventId, updatedEvent);

        assertEquals(updatedEvent.getEventName(), result.getEventName());
    }
    @Test
    void testGetAllEvents() {
        List<EventDAO> events = new ArrayList<>();
        events.add(new EventDAO());
        events.add(new EventDAO());
        when(eventRepository.findAll()).thenReturn(events);

        List<EventDAO> result = eventService.getAllEvents();

        assertEquals(events.size(), result.size());
    }

    @Test
    void testCreateEvent() {
        EventDAO event = new EventDAO();
        when(eventRepository.save(any(EventDAO.class))).thenReturn(event);
        UserDAO user = new UserDAO();
        user.setUserId(1L);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        EventDAO createdEvent = eventService.createEvent(event);

        assertNotNull(createdEvent);
        verify(userEventService, times(1)).addUserToEvent(any(UserEventDAO.class));
    }

    @Test
    void testGetEventsByUserId() {
        Long userId = 1L;
        UserDAO user = new UserDAO();
        user.setUserId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        List<EventDAO> events = new ArrayList<>();
        events.add(new EventDAO());
        events.add(new EventDAO());
        when(eventRepository.findByUserId(user)).thenReturn(events);

        List<EventDTO> result = eventService.getEventsByUserId(userId);

        assertEquals(events.size(), result.size());
    }
    //Deletion tests
    @Test
    public void testDeleteEventById_Success() {
        Long eventId = 1L;
        EventDAO event = new EventDAO();
        event.setEventId(eventId);
        event.setEventActive(false);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(userEventRepository.countByEventId(event)).thenReturn(1L);

        eventService.deleteEventById(eventId);

        verify(eventRepository, times(1)).delete(event);
    }

    @Test
    public void testDeleteEventById_NotFinished() {
        Long eventId = 1L;
        EventDAO event = new EventDAO();
        event.setEventId(eventId);
        event.setEventActive(true);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            eventService.deleteEventById(eventId);
        });

        assertEquals("Event cannot be deleted as it is not finished or has more than one user.", exception.getMessage());
        verify(eventRepository, never()).delete(event);
    }

    @Test
    public void testDeleteEventById_MoreThanOneUser() {
        Long eventId = 1L;
        EventDAO event = new EventDAO();
        event.setEventId(eventId);
        event.setEventActive(false);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(userEventRepository.countByEventId(event)).thenReturn(2L);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            eventService.deleteEventById(eventId);
        });

        assertEquals("Event cannot be deleted as it is not finished or has more than one user.", exception.getMessage());
        verify(eventRepository, never()).delete(event);
    }

    @Test
    public void testDeleteEventById_EventNotFound() {
        Long eventId = 1L;

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            eventService.deleteEventById(eventId);
        });

        verify(eventRepository, never()).delete(any(EventDAO.class));
    }
}