package com.hivetech.taskmanager.tasks.service;

import com.hivetech.taskmanager.common.exception.CustomException;
import com.hivetech.taskmanager.tasks.dto.TaskRequestDTO;
import com.hivetech.taskmanager.tasks.model.Task;
import com.hivetech.taskmanager.tasks.repository.TaskRepository;
import com.hivetech.taskmanager.user.model.User;
import com.hivetech.taskmanager.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new CustomException("Task with id" + id + "not found", HttpStatus.NOT_FOUND));

        return task;
    }

    @Override
    @Transactional
    public Task save(TaskRequestDTO taskDTO) {
        User user = userRepository.findById(taskDTO.getUserId())
                .orElseThrow(() -> new CustomException("User not found while saving the task", HttpStatus.NOT_FOUND));

        return taskRepository.save(modelMapper.map(taskDTO, Task.class));
    }

    @Override
    @Transactional
    public void delete(long id) {
        taskRepository.delete(taskRepository.findById(id).get());
    }
}
