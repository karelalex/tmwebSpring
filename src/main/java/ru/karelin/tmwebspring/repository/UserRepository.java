package ru.karelin.tmwebspring.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import ru.karelin.tmwebspring.entity.User;

import java.util.*;

@Repository
public class UserRepository {

    private final Map<String, User> userMap = new LinkedHashMap<>();



    public User find(String userId) {
        return userMap.get(userId);
    }

    public void save(User user) {
        userMap.put(user.getId(), user);
    }

    public void remove(@NotNull User user) {
        userMap.remove(user.getId());
    }

    public User findByLogin(String login) {
        for (User u : userMap.values()) {
            if (u.getLogin().equals(login)) return u;
        }
        return null;
    }
}
