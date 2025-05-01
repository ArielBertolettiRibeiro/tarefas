package com.example.demo.infrastructure.configs.mapper;

import com.example.demo.adapterts.dto.userDTO.UserRequestDTO;
import com.example.demo.adapterts.dto.userDTO.UserResponseDTO;
import com.example.demo.adapterts.dto.userDTO.UserSummaryDTO;
import com.example.demo.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public User toEntity(UserRequestDTO dto) {
        return  modelMapper.map(dto, User.class);
    }

    public UserResponseDTO toResponse(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public UserSummaryDTO toSummary(User user) {
        return modelMapper.map(user, UserSummaryDTO.class);
    }
}
