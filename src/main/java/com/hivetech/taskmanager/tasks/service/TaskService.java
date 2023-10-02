package com.hivetech.taskmanager.tasks.service;

import com.hivetech.taskmanager.tasks.dto.TaskRequestDTO;
import com.hivetech.taskmanager.tasks.model.Task;

import java.util.List;

public interface TaskService {

    Task save(TaskRequestDTO taskDTO);

    void delete(long id);

    List<Task> getAllByUserId(Long id);
}
