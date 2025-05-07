package com.example.demo.adapters.dto.noteDTO;

import com.example.demo.adapters.dto.userDTO.UserSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteResponseDTO {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private UserSummaryDTO author;
}
