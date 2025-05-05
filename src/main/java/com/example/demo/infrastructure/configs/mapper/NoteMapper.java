package com.example.demo.infrastructure.configs.mapper;

import com.example.demo.adapterts.dto.noteDTO.NoteRequestDTO;
import com.example.demo.adapterts.dto.noteDTO.NoteResponseDTO;
import com.example.demo.domain.entities.Note;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteMapper {

    private final ModelMapper modelMapper;

    public NoteResponseDTO toResponse(Note note) {
        return modelMapper.map(note, NoteResponseDTO.class);
    }

}
