package ru.karelin.tmwebspring.repository;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.karelin.tmwebspring.dto.ProjectDto;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ProjectDtoRepository extends CrudRepository<ProjectDto, String> {

    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    ProjectDto findByIdAndUserId(String id, String userId);

    List<ProjectDto> findAllByUserId(String id);
}
