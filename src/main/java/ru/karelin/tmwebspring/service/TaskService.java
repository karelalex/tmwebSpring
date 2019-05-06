package ru.karelin.tmwebspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karelin.tmwebspring.entity.Task;
import ru.karelin.tmwebspring.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {


    @Autowired
    private TaskRepository taskRepository;

    public Task findByIdAndUserId(String id, String userId) {
        return taskRepository.findByIdAndUserId(id, userId);
    }

    public List<Task> findAllByUserId(String userId) {
        return taskRepository.findAllByUserId(userId);
    }

    public List<Task> findAllByUserIdAndProjectId(String userId, String projectId) {
        return taskRepository.findAllByUserIdAndProjectId(userId, projectId);
    }

    public void save(Task task) {
        taskRepository.save(task);
    }

    public void remove(String id, String userId) {
        Task task = taskRepository.findByIdAndUserId(id, userId);
        if (task != null) taskRepository.remove(task);
    }
}
