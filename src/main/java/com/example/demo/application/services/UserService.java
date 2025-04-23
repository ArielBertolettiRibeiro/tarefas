package com.example.demo.application.services;

import com.example.demo.adapterts.dto.userDTO.UserRequestDTO;
import com.example.demo.adapterts.dto.userDTO.UserResponseDTO;
import com.example.demo.application.exceptions.DuplicateUserException;
import com.example.demo.application.exceptions.UserNotFoundException;
import com.example.demo.infrastructure.configs.mapstruct.UserMapper;
import com.example.demo.infrastructure.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserResponseDTO created(UserRequestDTO request) {

        if (repository.existsByNameAndEmail(request.getName(), request.getEmail())) {
            throw new DuplicateUserException("User with that name and email already exists.");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    public UserResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
