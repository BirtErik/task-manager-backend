package com.hivetech.taskmanager.tasks.dto;

import com.hivetech.taskmanager.tasks.enums.Priority;
import com.hivetech.taskmanager.tasks.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskRequestDTO {
    private long userId;
    private String name;
    private String description;
    private Priority priority;
    private Status status;

}
