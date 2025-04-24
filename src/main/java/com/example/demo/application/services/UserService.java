package com.example.demo.application.services;

import com.example.demo.adapterts.dto.userDTO.UserRequestDTO;
import com.example.demo.adapterts.dto.userDTO.UserResponseDTO;
import com.example.demo.application.exceptions.DuplicateUserException;
import com.example.demo.application.exceptions.UserNotFoundException;
import com.example.demo.domain.entities.User;
import com.example.demo.infrastructure.configs.mapstruct.UserMapper;
import com.example.demo.infrastructure.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found."));
    }

    public Page<UserResponseDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    public UserResponseDTO update(Long id, UserRequestDTO request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found."));

        if (!user.getEmail().equals(request.getEmail()) &&
                repository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException("Email already in use.");
        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return mapper.toResponse(repository.save(user));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }

        repository.deleteById(id);
    }
}
