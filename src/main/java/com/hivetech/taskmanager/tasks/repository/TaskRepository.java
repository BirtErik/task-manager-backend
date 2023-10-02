package com.hivetech.taskmanager.tasks.repository;

import com.hivetech.taskmanager.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId_Id(Long userId);
}
