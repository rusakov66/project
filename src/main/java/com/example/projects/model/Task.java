package com.example.projects.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "project_id")
    private UUID projectId;

    @Column(name = "status")
    private String status; // for example, "in progress", "done"

    @Column(name = "assigned_user_id")
    private UUID assignedUserId;

    // Constructors
    public Task() {
    }

    public Task(UUID id, String title, String description, LocalDate dueDate, UUID projectId, String status, UUID assignedUserId) {
        this.id = id;
        this.name = title;
        this.description = description;
        this.dueDate = dueDate;
        this.projectId = projectId;
        this.status = status;
        this.assignedUserId = assignedUserId;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(UUID assignedUserId) {
        this.assignedUserId = assignedUserId;
    }
}



