package com.example.demo.adapterts.dto.taskDTO;

import com.example.demo.domain.enums.Priority;
import com.example.demo.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskRequestDTO {

    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private LocalDate dueDate;
    private Long createdById;
    private Long assignedToId;
}
