package com.example.demo.adapterts.dto.noteDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteRequestDTO {

    private String content;
    private Long taskId;
    private Long authorId;
}
