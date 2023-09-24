package com.hivetech.taskmanager.tasks.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hivetech.taskmanager.tasks.dto.TaskRequestDTO;
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

    public Task(String name, String description, Priority priority, Status status) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public static Task fromRequestDTO(TaskRequestDTO requestDTO, User user) {
        Task task = new Task();
        task.setUserId(user);
        task.setName(requestDTO.getName());
        task.setDescription(requestDTO.getDescription());
        task.setPriority(requestDTO.getPriority());
        task.setStatus(requestDTO.getStatus());
        return task;
    }
}
