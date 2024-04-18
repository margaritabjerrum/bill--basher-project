package com.billbasher;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.billbasher.model.EventDAO;
import com.billbasher.model.UserDAO;
import com.billbasher.model.UserEventDAO;
import com.billbasher.repository.EventRep;
import com.billbasher.repository.UserEventRep;
import com.billbasher.repository.UserRep;
import com.billbasher.services.UserEventService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEventServiceTests {

    @Mock
    private UserRep userRep;

    @Mock
    private EventRep eventRep;

    @Mock
    private UserEventRep userEventRep;

    @InjectMocks
    private UserEventService userEventService;

    @Test
    public void testAddUserToEvent_UserAndEventExist() {

        UserDAO user = new UserDAO();
        user.setUserId(1L);

        EventDAO event = new EventDAO();
        event.setEventId(2L);

        UserEventDAO userEvent = new UserEventDAO();
        userEvent.setUserId(user);
        userEvent.setEventId(event);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userEventService.addUserToEvent(userEvent));

        assertEquals("User with ID 1 not found.", exception.getMessage());
    }

    @Test
    public void testAddUserToEvent_UserNotExist() {

        UserEventDAO userEvent = new UserEventDAO();
        UserDAO user = new UserDAO();
        user.setUserId(1L);
        userEvent.setUserId(user);

        EventDAO event = new EventDAO();
        event.setEventId(1L);
        userEvent.setEventId(event);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userEventService.addUserToEvent(userEvent));
        assertEquals("User with ID 1 not found.", exception.getMessage());
    }

    @Test
    public void testAddUserToEvent_EventNotExist() {

        UserEventDAO userEvent = new UserEventDAO();

        UserDAO user = new UserDAO();
        user.setUserId(1L);
        userEvent.setUserId(user);

        EventDAO event = new EventDAO();
        event.setEventId(1L);
        userEvent.setEventId(event);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userEventService.addUserToEvent(userEvent));
        assertEquals("User with ID 1 not found.", exception.getMessage());
    }

    @Test
    public void testRemoveUserFromEvent_UserEventExists() {

        Long userId = 1L;
        Long eventId = 1L;


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userEventService.removeUserFromEvent(userId, eventId));
        assertEquals("User with ID 1 not found.", exception.getMessage());
    }


}

