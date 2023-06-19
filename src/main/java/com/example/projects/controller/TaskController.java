package com.example.projects.controller;

import com.example.projects.model.Task;
import com.example.projects.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/assigned/{userId}")
    public ResponseEntity<List<Task>> getTasksAssignedToUser(@PathVariable UUID userId) {
        List<Task> tasks = taskService.getTasksAssignedToUser(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    // Метод для получения списка задач по ID проекта.
    // Возвращает HTTP-статус 200 (OK) и список задач в теле ответа.
    @GetMapping
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable UUID projectId) {
        return new ResponseEntity<>(taskService.getTasksByProjectId(projectId), HttpStatus.OK);
    }

    // Метод для получения конкретной задачи по её ID.
    // Возвращает HTTP-статус 200 (OK) и задачу в теле ответа.
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable UUID taskId) {
        return new ResponseEntity<>(taskService.getTaskById(taskId), HttpStatus.OK);
    }

    // Метод для создания новой задачи в рамках определенного проекта.
    // Принимает в теле запроса объект задачи и возвращает HTTP-статус 201 (Created) и созданную задачу в теле ответа.

    @PostMapping
    public ResponseEntity<Task> createTask(@PathVariable UUID projectId, @RequestBody Task task) {
        task.setId(UUID.randomUUID());
        task.setProjectId(projectId);
        System.out.println(task.getAssignedUserId());
        return new ResponseEntity<>(taskService.createTask(task), HttpStatus.CREATED);
    }


    // Метод для обновления задачи по её ID и ID проекта.
    // Принимает в теле запроса объект задачи с обновленными данными и возвращает HTTP-статус 200 (OK) и обновленную задачу в теле ответа.
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID taskId, @PathVariable UUID projectId, @RequestBody Task task) {
        return new ResponseEntity<>(taskService.updateTask(task), HttpStatus.OK);
    }

    // Метод для удаления задачи по её ID.
    // Возвращает HTTP-статус 204 (No Content) в случае успешного удаления.
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Метод для назначения задачи конкретному пользователю.
    // Принимает ID задачи и ID пользователя, которому назначается задача, и возвращает HTTP-статус 200 (OK).
    @PutMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<Void> assignTaskToUser(@PathVariable UUID taskId, @PathVariable UUID userId) {
        taskService.assignTaskToUser(taskId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskAsComplete(@PathVariable UUID taskId) {
        Task updatedTask = taskService.markTaskAsComplete(taskId);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
