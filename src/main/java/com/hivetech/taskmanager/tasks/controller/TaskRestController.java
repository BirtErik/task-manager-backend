package com.hivetech.taskmanager.tasks.controller;

import com.hivetech.taskmanager.tasks.dto.TaskRequestDTO;
import com.hivetech.taskmanager.tasks.model.Task;
import com.hivetech.taskmanager.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskRestController {
    private final TaskService taskService;

    @Autowired
    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Task>> getAllByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(taskService.getAllByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> save(@RequestBody TaskRequestDTO taskDTO) {
        return new ResponseEntity<>(taskService.save(taskDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.delete(taskId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
