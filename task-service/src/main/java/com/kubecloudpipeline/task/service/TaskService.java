package com.kubecloudpipeline.task.service;

import com.kubecloudpipeline.task.model.Task;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service layer for managing Task business logic and data storage.
 * Uses an in-memory ConcurrentHashMap for Sprint-02.
 */
@ApplicationScoped
public class TaskService {

    // In-memory storage for tasks
    private final Map<String, Task> taskRepository = new ConcurrentHashMap<>();

    /**
     * Retrieves all tasks.
     * @return List of tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(taskRepository.values());
    }

    /**
     * Retrieves a task by its ID.
     * @param id The ID of the task
     * @return Optional containing the task if found
     */
    public Optional<Task> getTaskById(String id) {
        return Optional.ofNullable(taskRepository.get(id));
    }

    /**
     * Creates a new task.
     * @param task The task to create
     * @return The created task
     */
    public Task createTask(Task task) {
        if (task.getId() == null) {
            // Task constructor generates ID, but if passed empty, we can re-init or trust the caller/DTO mapping
            // For now, the model constructor handles ID generation.
        }
        taskRepository.put(task.getId(), task);
        return task;
    }

    /**
     * Updates an existing task.
     * @param id The ID of the task to update
     * @param updatedTask The updated task data
     * @return Optional containing the updated task if found
     */
    public Optional<Task> updateTask(String id, Task updatedTask) {
        return Optional.ofNullable(taskRepository.computeIfPresent(id, (key, existingTask) -> {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setUpdatedAt(LocalDateTime.now());
            return existingTask;
        }));
    }

    /**
     * Deletes a task by its ID.
     * @param id The ID of the task to delete
     * @return true if the task was deleted, false otherwise
     */
    public boolean deleteTask(String id) {
        return taskRepository.remove(id) != null;
    }
}
