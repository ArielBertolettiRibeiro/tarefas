package com.example.demo.infrastructure.configs.mapper;

import com.example.demo.adapterts.dto.taskDTO.TaskRequestDTO;
import com.example.demo.adapterts.dto.taskDTO.TaskResponseDTO;
import com.example.demo.adapterts.dto.taskDTO.TaskSummaryDTO;
import com.example.demo.domain.entities.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final ModelMapper modelMapper;

    public Task toEntity(TaskRequestDTO dto) {
        return modelMapper.map(dto, Task.class);
    }

    public TaskResponseDTO toResponse(Task task) {
        return modelMapper.map(task, TaskResponseDTO.class);
    }

    public TaskSummaryDTO toSummary(Task task) {
        return modelMapper.map(task, TaskSummaryDTO.class);
    }
}
