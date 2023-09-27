package com.hivetech.taskmanager.user.dto;

import com.hivetech.taskmanager.tasks.model.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private List<Task> tasks;
}
