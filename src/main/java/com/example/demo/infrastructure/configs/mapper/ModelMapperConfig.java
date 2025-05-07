package com.example.demo.infrastructure.configs.mapper;

import com.example.demo.adapters.dto.taskDTO.TaskResponseDTO;
import com.example.demo.adapters.dto.userDTO.UserSummaryDTO;
import com.example.demo.domain.entities.Task;
import com.example.demo.domain.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(User.class, UserSummaryDTO.class);

        mapper.createTypeMap(Task.class, TaskResponseDTO.class);

        return mapper;
    }

}
