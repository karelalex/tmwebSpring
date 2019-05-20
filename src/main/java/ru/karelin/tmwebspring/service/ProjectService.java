package ru.karelin.tmwebspring.service;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karelin.tmwebspring.entity.Project;
import ru.karelin.tmwebspring.entity.Task;
import ru.karelin.tmwebspring.repository.ProjectRepository;
import ru.karelin.tmwebspring.repository.TaskRepository;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;

    public void save(Project project) {
        if (project == null) return;
        projectRepository.save(project);
    }

    public void remove(@Nullable String projectId, String userId) {
        if (projectId == null || projectId.isEmpty()) return;
        Project project = projectRepository.findByIdAndUserId(projectId, userId);
        if (project != null) {
            projectRepository.delete(project);
        }
    }

    public List<Project> findAllByUserId(String userId) {
        if( userId==null || userId.isEmpty()) return Collections.emptyList();
        return projectRepository.findAllByUserId(userId);
    }

    public Project findByIdAndUserId(String id, String userId) {
        if(id==null || userId==null || id.isEmpty() || userId.isEmpty()) return null;
        return projectRepository.findByIdAndUserId(id, userId);
    }
}
