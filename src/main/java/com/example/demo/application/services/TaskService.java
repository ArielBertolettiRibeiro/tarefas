package com.example.demo.application.services;

import com.example.demo.adapterts.dto.taskDTO.TaskRequestDTO;
import com.example.demo.adapterts.dto.taskDTO.TaskResponseDTO;
import com.example.demo.application.exceptions.UserNotFoundException;
import com.example.demo.domain.entities.Task;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.enums.Status;
import com.example.demo.infrastructure.configs.mapper.TaskMapper;
import com.example.demo.infrastructure.repositorys.TaskRepository;
import com.example.demo.infrastructure.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final TaskMapper mapper;

    public TaskResponseDTO created(TaskRequestDTO request) {
        Task task = mapper.toEntity(request);

        User createdBy = userRepository.findById(request.getCreatedById())
                .orElseThrow(() -> new UserNotFoundException("Creator not found."));

        User assignedTo = userRepository.findById(request.getAssignedToId())
                .orElseThrow(() -> new UserNotFoundException("Assignee not found."));


        task.setCreatedBy(createdBy);
        task.setAssignedTo(assignedTo);
        task.setStatus(Status.TO_DO);
        task.setCreatedAt(LocalDateTime.now());

        return mapper.toResponse(repository.save(task));
    }
}
