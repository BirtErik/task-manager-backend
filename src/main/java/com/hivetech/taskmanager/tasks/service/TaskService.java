package com.hivetech.taskmanager.tasks.service;

import com.hivetech.taskmanager.tasks.dto.TaskRequestDTO;
import com.hivetech.taskmanager.tasks.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();

    Task findById(long id);

    Task save(TaskRequestDTO taskDTO);

    void delete(long id);
}
