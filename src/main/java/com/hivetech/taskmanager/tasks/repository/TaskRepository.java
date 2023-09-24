package com.hivetech.taskmanager.tasks.repository;

import com.hivetech.taskmanager.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
