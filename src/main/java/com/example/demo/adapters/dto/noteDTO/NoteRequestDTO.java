package com.example.demo.adapters.dto.noteDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteRequestDTO {

    @NotBlank(message = "Content is required")
    private String content;

    @NotNull(message = "Author ID is required")
    private Long authorId;
}
