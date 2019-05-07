package ru.karelin.tmwebspring.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.karelin.tmwebspring.entity.Project;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    @PersistenceContext
    EntityManager em;


    @Override
    public List<Project> findAllByUserId(String userId) {
        return em.createQuery("select p from Project p where  p.user.id = :uid", Project.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public Project find(String id) {
        return em.find(Project.class, id);
    }

    @Override
    public Project findByIdAndUserId(String id, String userId) {
        List<Project> list = em.createQuery("select p from Project p where p.id = :id and p.user.id = :uid", Project.class)
                .setParameter("id", id)
                .setParameter("uid", userId)
                .getResultList();
        if (list.size() > 0) return list.get(0);
        return null;
    }

    @Override
    public void save(Project p) {
        em.merge(p);
    }

    @Override
    public void remove(@NotNull Project project) {
        em.remove(project);
    }


}
