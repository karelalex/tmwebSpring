package ru.karelin.tmwebspring.repository;

import ru.karelin.tmwebspring.entity.Task;

import java.util.List;

public interface TaskRepository extends EntityRepository<Task> {
    List<Task> findAllByUserIdAndProjectId(String userId, String projectId);
}
