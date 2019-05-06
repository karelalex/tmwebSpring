package ru.karelin.tmwebspring.repository;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.karelin.tmwebspring.entity.Task;

import java.util.*;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final HashMap<String, Task> taskMap = new LinkedHashMap<>();

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(taskMap.values());
    }

    @Override
    public List<Task> findAllByUserId(String userId) {
        return filterByUserId(findAll(), userId);
    }

    @Override
    public Task find(String id) {
        return taskMap.get(id);
    }

    @Override
    public Task findByIdAndUserId(String id, String userId) {
        return filterByUserId(find(id), userId);
    }

    @Override
    public void save(Task t) {
        taskMap.put(t.getId(), t);
    }

    @Override
    public void remove(Task t) {
        taskMap.remove(t.getId());
    }

    @Override
    public List<Task> findAllByUserIdAndProjectId(String userId, String projectId) {
        List<Task> list = findAllByUserId(userId);
        list.removeIf(task -> !task.getProjectId().equals(projectId));
        return list;
    }

    private List<Task> filterByUserId(@NotNull List<Task> tasks, String userId) {
        tasks.removeIf(task -> !task.getUserId().equals(userId));
        return tasks;
    }

    @Contract("null, _ -> null")
    @Nullable
    private Task filterByUserId(@Nullable Task task, String userId) {
        if (task != null && task.getUserId().equals(userId)) return task;
        else return null;
    }
}
