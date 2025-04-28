package com.example.demo.adapterts.controllers;

import com.example.demo.adapterts.dto.taskDTO.TaskRequestDTO;
import com.example.demo.adapterts.dto.taskDTO.TaskResponseDTO;
import com.example.demo.application.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> created(@Valid @RequestBody TaskRequestDTO request, UriComponentsBuilder uriBuilder) {
        TaskResponseDTO response = service.created(request);

        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
