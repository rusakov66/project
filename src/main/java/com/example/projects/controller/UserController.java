package com.example.projects.controller;

import com.example.projects.model.Task;
import com.example.projects.model.User;
import com.example.projects.repository.UserRepository;
import com.example.projects.services.TaskService;
import com.example.projects.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UUID registerUser(@RequestBody User user) {
        user.setId(UUID.randomUUID());
        userRepository.save(user);
        return user.getId();
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<User> getUserProfile(@PathVariable UUID id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(@RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity<User> updatePassword(@RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Метод для получения списка задач, назначенных определенному пользователю
    @GetMapping("/assigned/{userId}")
    public ResponseEntity<List<Task>> getTasksAssignedToUser(@PathVariable UUID userId) {
        List<Task> tasks = taskService.getTasksAssignedToUser(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}


