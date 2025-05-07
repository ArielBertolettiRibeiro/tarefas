package com.example.demo.application.services;

import com.example.demo.adapters.dto.noteDTO.NoteRequestDTO;
import com.example.demo.adapters.dto.noteDTO.NoteResponseDTO;
import com.example.demo.application.exceptions.TaskNotFoundException;
import com.example.demo.application.exceptions.UserNotFoundException;
import com.example.demo.domain.entities.Note;
import com.example.demo.domain.entities.Task;
import com.example.demo.domain.entities.User;
import com.example.demo.infrastructure.configs.mapper.NoteMapper;
import com.example.demo.infrastructure.repositorys.NoteRepository;
import com.example.demo.infrastructure.repositorys.TaskRepository;
import com.example.demo.infrastructure.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;
    private final NoteMapper mapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Transactional
    public NoteResponseDTO create(Long taskId, NoteRequestDTO request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found."));

        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        Note note = new Note(request.getContent(), task, author);
        note.setTask(task);
        note.setAuthor(author);
        note.setCreatedAt(LocalDateTime.now());

        return mapper.toResponse(repository.save(note));
    }

    @Transactional(readOnly = true)
    public List<NoteResponseDTO> findAllByTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new TaskNotFoundException("Task not found.");
        }

        return repository.findAllByTaskId(taskId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

}
