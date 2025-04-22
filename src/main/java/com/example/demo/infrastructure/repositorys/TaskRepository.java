package com.example.demo.infrastructure.repositorys;

import com.example.demo.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
