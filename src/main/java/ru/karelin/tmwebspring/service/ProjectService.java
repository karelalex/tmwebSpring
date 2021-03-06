package ru.karelin.tmwebspring.service;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karelin.tmwebspring.entity.Project;
import ru.karelin.tmwebspring.entity.Task;
import ru.karelin.tmwebspring.repository.ProjectRepository;
import ru.karelin.tmwebspring.repository.TaskRepository;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;

    public void save(Project project) {
        projectRepository.save(project);
    }

    public void remove(@Nullable String projectId, String userId) {
        if (projectId == null || projectId.isEmpty()) return;
        Project project = projectRepository.findByIdAndUserId(projectId, userId);
        if (project != null) {
            List<Task> tasks = taskRepository.findAllByUserIdAndProjectId(userId, projectId);
            for (Task t : tasks
            ) {
                taskRepository.remove(t);
            }
            projectRepository.remove(project);
        }
    }

    public List<Project> findAllByUserId(String id) {
        return projectRepository.findAllByUserId(id);
    }

    public Project findByIdAndUserId(String id, String userId) {
        return projectRepository.findByIdAndUserId(id, userId);
    }
}
