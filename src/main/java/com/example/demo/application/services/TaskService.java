package com.example.demo.application.services;

import com.example.demo.adapters.dto.taskDTO.TaskRequestDTO;
import com.example.demo.adapters.dto.taskDTO.TaskResponseDTO;
import com.example.demo.adapters.dto.taskDTO.TaskSummaryDTO;
import com.example.demo.application.exceptions.TaskNotFoundException;
import com.example.demo.application.exceptions.UserNotFoundException;
import com.example.demo.domain.entities.Task;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.enums.Status;
import com.example.demo.infrastructure.configs.mapper.TaskMapper;
import com.example.demo.infrastructure.repositorys.TaskRepository;
import com.example.demo.infrastructure.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final TaskMapper mapper;

    @Transactional
    public TaskResponseDTO create(TaskRequestDTO request) {

        User createdBy = userRepository.findById(request.getCreatedById())
                .orElseThrow(() -> new UserNotFoundException("Creator not found."));

        User assignedTo = userRepository.findById(request.getAssignedToId())
                .orElseThrow(() -> new UserNotFoundException("Assignee not found."));

        Task task = mapper.toEntity(request, createdBy, assignedTo);

        return mapper.toResponse(repository.save(task));
    }

    @Transactional(readOnly = true)
    public TaskResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new TaskNotFoundException("Task not found."));
    }

    @Transactional
    public TaskResponseDTO update(Long id, TaskRequestDTO requestDTO) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found."));

        task.setDescription(requestDTO.getDescription());
        task.setTitle(requestDTO.getTitle());
        task.setPriority(requestDTO.getPriority());
        task.setDueDate(requestDTO.getDueDate());

        return mapper.toResponse(repository.save(task));
    }

    @Transactional
    public TaskResponseDTO assignTask(Long id, Long assigneeId) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found."));

        User user = userRepository.findById(assigneeId)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        task.setAssignedTo(user);

        return mapper.toResponse(repository.save(task));
    }

    @Transactional
    public TaskResponseDTO updateStatus(Long taskId, Status status) {
        Task task = repository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found."));

        task.setStatus(status);
        return mapper.toResponse(repository.save(task));
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException("Task not found.");
        }

        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<TaskSummaryDTO> findTaskByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        List<Task> tasks = repository.findByCreatedBy(user);
        return tasks.stream()
                .map(mapper::toSummary)
                .toList();
    }
}
