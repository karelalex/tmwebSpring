package ru.karelin.tmwebspring.repository;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.karelin.tmwebspring.entity.Project;

import java.util.*;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    private final Map<String, Project> projectMap = new LinkedHashMap<>();


    @Override
    public List<Project> findAll() {
        return new ArrayList<>(projectMap.values());
    }

    @Override
    public List<Project> findAllByUserId(String userId) {
        return filterByUserId(findAll(), userId);
    }

    @Override
    public Project find(String id) {
        return projectMap.get(id);
    }

    @Override
    public Project findByIdAndUserId(String id, String userId) {
        return filterByUserId(find(id), userId);
    }

    @Override
    public void save(Project p) {
        projectMap.put(p.getId(), p);
    }

    @Override
    public void remove(@NotNull Project project) {
        projectMap.remove(project.getId());
    }

    private List<Project> filterByUserId(List<Project> projects, String userId) {
        projects.removeIf(p -> !p.getUserId().equals(userId));
        return projects;
    }

    @Contract("null, _ -> null")
    @Nullable
    private Project filterByUserId(@Nullable Project project, String userId) {
        if (project != null && project.getUserId().equals(userId)) return project;
        else return null;
    }
}
