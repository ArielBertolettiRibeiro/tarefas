package com.example.demo.application.services;

import com.example.demo.adapterts.dto.taskDTO.TaskRequestDTO;
import com.example.demo.adapterts.dto.taskDTO.TaskResponseDTO;
import com.example.demo.infrastructure.repositorys.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository repository;

}
