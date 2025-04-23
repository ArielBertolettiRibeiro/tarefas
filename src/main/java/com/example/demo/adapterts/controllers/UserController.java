package com.example.demo.adapterts.controllers;

import com.example.demo.adapterts.dto.userDTO.UserRequestDTO;
import com.example.demo.adapterts.dto.userDTO.UserResponseDTO;
import com.example.demo.application.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<UserResponseDTO> created(@Valid @RequestBody UserRequestDTO request, UriComponentsBuilder builder) {
        UserResponseDTO response = service.created(request);

        var uri = builder.path("/users/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
