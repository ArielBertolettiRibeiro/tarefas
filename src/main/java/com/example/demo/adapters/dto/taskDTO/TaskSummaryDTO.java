package com.example.demo.adapters.dto.taskDTO;

import com.example.demo.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskSummaryDTO {

    private Long id;
    private String title;
    private Status status;
}
