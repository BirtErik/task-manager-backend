package com.hivetech.taskmanager.user.dto;

import com.hivetech.taskmanager.tasks.model.Task;
import com.hivetech.taskmanager.user.model.User;
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
    private List<Task> tasks;

    public static UserResponseDTO fromEntity(User user) {
        UserResponseDTO userDTO = new UserResponseDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setTasks(user.getTasks());
        return userDTO;
    }
}
