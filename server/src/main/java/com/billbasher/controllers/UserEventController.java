package com.billbasher.controllers;

import com.billbasher.services.UserEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEventController {

    private final UserEventService userEventService;

    public UserEventController(UserEventService userEventService) {
        this.userEventService = userEventService;
    }

    @PostMapping("/api/v1/user-event/add")
    public ResponseEntity<String> addUserToEvent(@RequestParam Long userId, @RequestParam Long eventId) {
        try {
            userEventService.addUserToEvent(userId, eventId);
            return ResponseEntity.ok("User successfully added to event.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding user to event.");
        }
    }
}
