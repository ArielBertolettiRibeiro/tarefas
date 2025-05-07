package com.example.demo.adapters.controllers;

import com.example.demo.adapters.dto.userDTO.UserRequestDTO;
import com.example.demo.adapters.dto.userDTO.UserResponseDTO;
import com.example.demo.adapters.dto.userDTO.UserSummaryDTO;
import com.example.demo.application.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> created(@Valid @RequestBody UserRequestDTO request, UriComponentsBuilder builder) {
        UserResponseDTO response = service.created(request);

        var uri = builder.path("/users/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<UserSummaryDTO>> findAll(@PageableDefault(size = 20, sort = "name")Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
