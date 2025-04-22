package com.example.demo.infrastructure.repositorys;

import com.example.demo.domain.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
