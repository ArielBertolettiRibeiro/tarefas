package com.example.demo.adapterts.controllers;

import com.example.demo.adapterts.dto.noteDTO.NoteRequestDTO;
import com.example.demo.adapterts.dto.noteDTO.NoteResponseDTO;
import com.example.demo.application.services.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NoteController {

    private final NoteService service;

    @PostMapping
    public ResponseEntity<NoteResponseDTO> create(@PathVariable Long taskId, @Valid @RequestBody NoteRequestDTO request) {
        NoteResponseDTO response = service.create(taskId, request);

        return ResponseEntity.created(URI.create("/tasks/" + taskId + "/notes/" + response.getId()))
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> findAllByTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(service.findAllByTask(taskId));
    }
}
