package com.example.demo.adapterts.dto.taskDTO;

import com.example.demo.adapterts.dto.noteDTO.NoteResponseDTO;
import com.example.demo.adapterts.dto.userDTO.UserSummaryDTO;
import com.example.demo.domain.enums.Priority;
import com.example.demo.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private UserSummaryDTO createdBy;
    private UserSummaryDTO assignedTo;
    private List<NoteResponseDTO> notes;
}
