package com.group5.activitytrackergroup5task.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@ToString
@Setter
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
@Entity
@Table(name="todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "status")
    private String status;
    @Column(name = "description")
    private String description;
    @Column(name = "scheduled_on")
    private String scheduledOn;
    @Column(name = "end_on")
    private String endOn;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
