package com.billbasher.controllers;

import com.billbasher.model.UserEventDAO;
import com.billbasher.services.UserEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserEventController {

    private final UserEventService userEventService;

    public UserEventController(UserEventService userEventService) {
        this.userEventService = userEventService;
    }

    @PostMapping("/api/v1/user-event/add")
    public ResponseEntity<String> addUserToEvent(@RequestBody UserEventDAO userEventDAO) {
        try {
            userEventService.addUserToEvent(userEventDAO);
            return ResponseEntity.ok("User successfully added to event.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding user to event.");
        }
    }

//    @DeleteMapping("/api/v1/user-event/remove/{userId}/{eventId}")
//    public ResponseEntity<String> removeUserFromEvent(@PathVariable Long userId, @PathVariable Long eventId) {
//        try {
//            userEventService.removeUserFromEvent(userId, eventId);
//            return ResponseEntity.ok("User successfully removed from event.");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while removing user from event.");
//        }
//    }

//    @DeleteMapping("/api/v1/user-event/remove")
//    public ResponseEntity<String> removeUserFromEvent(@RequestParam Long userId, @RequestParam Long eventId) {
//        try {
//            userEventService.removeUserFromEvent(userId, eventId);
//            return ResponseEntity.ok("User successfully removed from event.");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while removing user from event.");
//        }
//    }


}
