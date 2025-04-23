package com.example.demo.infrastructure.configs.mapstruct;

import com.example.demo.adapterts.dto.noteDTO.NoteRequestDTO;
import com.example.demo.adapterts.dto.noteDTO.NoteResponseDTO;
import com.example.demo.domain.entities.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface NoteMapper {

    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "taskId", target = "task.id")
    Note toEntity(NoteRequestDTO dto);

    NoteResponseDTO toResponse(Note note);
}
