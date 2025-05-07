package com.example.demo.adapters.controllers;

import com.example.demo.adapters.dto.taskDTO.TaskRequestDTO;
import com.example.demo.adapters.dto.taskDTO.TaskResponseDTO;
import com.example.demo.adapters.dto.taskDTO.TaskSummaryDTO;
import com.example.demo.application.services.TaskService;
import com.example.demo.domain.enums.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RequestMapping("/tasks")
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@Valid @RequestBody TaskRequestDTO request, UriComponentsBuilder uriBuilder) {
        TaskResponseDTO response = service.create(request);

        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(@PathVariable Long id,@Valid @RequestBody TaskRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @PatchMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<TaskResponseDTO> assignTask(@PathVariable Long taskId, @PathVariable Long userId) {
        return ResponseEntity.ok(service.assignTask(taskId, userId));
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<TaskResponseDTO> updateStatus(@PathVariable Long taskId, @RequestParam Status status) {
        return ResponseEntity.ok(service.updateStatus(taskId, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskSummaryDTO>> findTaskByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findTaskByUser(userId));
    }
}
