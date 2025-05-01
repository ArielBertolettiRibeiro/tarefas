package com.example.demo.infrastructure.repositorys;

import com.example.demo.domain.entities.Task;
import com.example.demo.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCreatedBy(User user);
}
