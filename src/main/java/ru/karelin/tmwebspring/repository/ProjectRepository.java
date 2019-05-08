package ru.karelin.tmwebspring.repository;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.karelin.tmwebspring.entity.Project;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {

    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    Project findByIdAndUserId(String id, String userId);

    List<Project> findAllByUserId(String id);
}
