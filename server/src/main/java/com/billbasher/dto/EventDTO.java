package com.billbasher.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDTO {
    private Long eventId;
    private String eventName;

    public EventDTO(Long eventId, String eventName) {
        this.eventId = eventId;
        this.eventName = eventName;
    }
}
