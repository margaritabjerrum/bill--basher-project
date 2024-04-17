package com.billbasher.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
@Entity
public class EventDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId")
    private Long eventId;
    private Boolean eventActive;
    private String eventName;
    private LocalDateTime eventCreated = LocalDateTime.now();

}
