package ru.karelin.tmwebspring.repository;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.karelin.tmwebspring.dto.TaskDto;
import ru.karelin.tmwebspring.entity.Task;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface TaskDtoRepository extends CrudRepository<TaskDto, String> {
    List<TaskDto> findAllByUserIdAndProjectId(String userId, String projectId);

    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    TaskDto findByIdAndUserId(String id, String userId);

    List<TaskDto> findAllByUserId(String userId);
}
