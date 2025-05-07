package com.example.demo.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Notes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @PrePersist
    public  void createdAt() {
        createdAt = LocalDateTime.now();
    }

    public Note(String content, Task task, User author) {
        this.content = content;
        this.task = task;
        this.author = author;
    }
}
