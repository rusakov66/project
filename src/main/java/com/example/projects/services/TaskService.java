package com.example.projects.services;

import com.example.projects.model.Task;
import com.example.projects.repository.TaskRepository;
import com.example.projects.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksByProjectId(UUID projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public Task getTaskById(UUID taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }

    // Метод для получения списка задач, назначенных определенному пользователю
    public List<Task> getTasksAssignedToUser(UUID userId) {
        return taskRepository.findByAssignedUserId(userId);
    }

    public void assignTaskToUser(UUID taskId, UUID userId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            task.setAssignedUserId(userId);
            taskRepository.save(task);
        }
    }

    public Task markTaskAsComplete(UUID taskId) {
        // Получить задачу по taskId из репозитория
        Task task = taskRepository.findById(taskId).orElse(null);

        if (task != null) {
            // Установить статус задачи как выполненный
            task.setStatus("Completed");

            // Дополнительные операции, если необходимо

            // Сохранить обновленную задачу в репозитории
            return taskRepository.save(task);
        } else {
            // Обработка случая, когда задача не найдена
            return null;
        }
    }

}




