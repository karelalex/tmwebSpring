package ru.karelin.tmwebspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karelin.tmwebspring.dto.TaskDto;
import ru.karelin.tmwebspring.entity.Task;
import ru.karelin.tmwebspring.repository.TaskDtoRepository;
import ru.karelin.tmwebspring.repository.TaskRepository;

import java.util.List;

@Service
@Transactional
public class TaskDtoService {


    @Autowired
    private TaskDtoRepository taskDtoRepository;

    @Autowired
    TaskRepository taskRepository;

    public TaskDto findByIdAndUserId(String id, String userId) {
        return taskDtoRepository.findByIdAndUserId(id, userId);
    }

    public List<TaskDto> findAllByUserId(String userId) {
        return taskDtoRepository.findAllByUserId(userId);
    }

    public List<TaskDto> findAllByUserIdAndProjectId(String userId, String projectId) {
        return taskDtoRepository.findAllByUserIdAndProjectId(userId, projectId);
    }

    public void save(TaskDto task) {
        taskDtoRepository.save(task);
    }

    public boolean remove(String id, String userId) {
        Task task = taskRepository.findByIdAndUserId(id, userId);
        if (task == null)return false;
        else taskRepository.delete(task);
        return true;
    }
}
