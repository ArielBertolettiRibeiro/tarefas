package com.example.demo.infrastructure.repositorys;

import com.example.demo.domain.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByTaskId(Long taskId);
}
