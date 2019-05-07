package ru.karelin.tmwebspring.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.karelin.tmwebspring.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class UserRepository {

    private final Map<String, User> userMap = new LinkedHashMap<>();

    @PersistenceContext
    EntityManager em;


    public User find(String userId) {
        return em.find(User.class, userId);
    }

    public void save(User user) {em.merge(user);}

    public void remove(@NotNull User user) {
        em.remove(user);
    }

    public User findByLogin(String login) {
        List<User> list = em.createQuery("select u from User u where u.login = :login", User.class)
                .setParameter("login", login)
                .getResultList();
        if(list.size()>0) {
            return list.get(0);
        }
        return null;
    }
}
