package com.example.demo.infrastructure.configs.mapstruct;

import com.example.demo.adapterts.dto.taskDTO.TaskRequestDTO;
import com.example.demo.adapterts.dto.taskDTO.TaskResponseDTO;
import com.example.demo.adapterts.dto.taskDTO.TaskSummaryDTO;
import com.example.demo.domain.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, NoteMapper.class})
public interface TaskMapper {

    @Mapping(source = "createdById", target = "createdBy.id")
    @Mapping(source = "assignedToId", target = "assignedTo.id")
    Task toEntity(TaskRequestDTO dto);

    TaskResponseDTO toResponse(Task task);

    TaskSummaryDTO toSummary(Task task);
}
