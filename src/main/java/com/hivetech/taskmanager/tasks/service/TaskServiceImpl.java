package com.hivetech.taskmanager.tasks.service;

import com.hivetech.taskmanager.tasks.dto.TaskRequestDTO;
import com.hivetech.taskmanager.tasks.model.Task;
import com.hivetech.taskmanager.tasks.repository.TaskRepository;
import com.hivetech.taskmanager.user.model.User;
import com.hivetech.taskmanager.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(long id) {
        Optional<Task> task = taskRepository.findById(id);

        if (task.isEmpty()) {
            throw new RuntimeException("Task not found");
        }
        return task.get();
    }

    @Override
    @Transactional
    public Task save(TaskRequestDTO taskDTO) {
        User user = userRepository.findById(taskDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found while saving task"));
        Task task = Task.fromRequestDTO(taskDTO, user);

        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task with id" + id + "not found");
        }
        taskRepository.deleteById(id);
    }
}
