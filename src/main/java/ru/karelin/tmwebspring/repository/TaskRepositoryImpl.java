package ru.karelin.tmwebspring.repository;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.karelin.tmwebspring.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

   @PersistenceContext
    EntityManager em;



    @Override
    public List<Task> findAllByUserId(String userId) {
        return em.createQuery("select t from Task t where t.user.id = :uid", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public Task find(String id) {
        return em.find(Task.class, id);
    }

    @Override
    public Task findByIdAndUserId(String id, String userId) {
        List<Task> list = em.createQuery("select t from Task t where t.id = :id and t.user.id = :uid", Task.class)
                .setParameter("id", id)
                .setParameter("uid", userId)
                .getResultList();
        if(list.size()>0) return list.get(0);
        return null;
    }

    @Override
    public void save(Task t) {
        em.merge(t);
    }

    @Override
    public void remove(Task t) {
        em.remove(t);
    }

    @Override
    public List<Task> findAllByUserIdAndProjectId(String userId, String projectId) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid", Task.class)
                .setParameter("uid", userId)
                .setParameter("pid", projectId)
                .getResultList();
    }

}
