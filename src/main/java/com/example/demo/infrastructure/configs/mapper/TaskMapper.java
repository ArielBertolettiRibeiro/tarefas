package com.example.demo.infrastructure.configs.mapper;

import com.example.demo.adapters.dto.taskDTO.TaskRequestDTO;
import com.example.demo.adapters.dto.taskDTO.TaskResponseDTO;
import com.example.demo.adapters.dto.taskDTO.TaskSummaryDTO;
import com.example.demo.domain.entities.Task;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.enums.Status;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final ModelMapper modelMapper;

    public Task toEntity(TaskRequestDTO dto, User createdBy, User assignedTo) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
        task.setCreatedBy(createdBy);
        task.setAssignedTo(assignedTo);
        task.setStatus(Status.TO_DO);
        return task;
    }

    public TaskResponseDTO toResponse(Task task) {
        return modelMapper.map(task, TaskResponseDTO.class);
    }

    public TaskSummaryDTO toSummary(Task task) {
        return modelMapper.map(task, TaskSummaryDTO.class);
    }
}
