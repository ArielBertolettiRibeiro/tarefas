package com.example.demo.infrastructure.configs.mapstruct;

import com.example.demo.adapterts.dto.userDTO.UserRequestDTO;
import com.example.demo.adapterts.dto.userDTO.UserResponseDTO;
import com.example.demo.adapterts.dto.userDTO.UserSummaryDTO;
import com.example.demo.domain.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDTO dto);

    UserResponseDTO toResponse(User user);

    UserSummaryDTO toSummary(User user);
}
