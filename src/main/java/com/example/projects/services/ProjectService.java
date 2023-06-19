package com.example.projects.services;

import com.example.projects.model.Project;
import com.example.projects.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return (List<Project>) projectRepository.findAll();
    }

    public Project getProjectById(UUID projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    public Project updateProject(UUID projectId, Project project) {
        Project existingProject = projectRepository.findById(projectId).orElse(null);

        if (existingProject != null) {
            existingProject.setName(project.getName());
            existingProject.setDescription(project.getDescription());
            existingProject.setStartDate(project.getStartDate());
            existingProject.setEndDate(project.getEndDate());
            return projectRepository.save(existingProject);
        }

        return null;
    }

    public void deleteProject(UUID projectId) {
        projectRepository.deleteById(projectId);
    }
}


