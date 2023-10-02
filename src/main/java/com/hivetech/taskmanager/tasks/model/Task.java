package com.hivetech.taskmanager.tasks.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hivetech.taskmanager.tasks.enums.Priority;
import com.hivetech.taskmanager.tasks.enums.Status;
import com.hivetech.taskmanager.user.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User userId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "priority")
    @Enumerated(value = EnumType.STRING)
    private Priority priority;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public Task(User userId, Long id, String name, String description, Priority priority, Status status) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public Task(User userId, String name, String description, Priority priority, Status status) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

}
