package ru.karelin.tmwebspring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.karelin.tmwebspring.entity.User;

public interface UserService extends UserDetailsService {
    User findByLoginAndPassword(String login, String password);

    User findByLogin(String login);

    User find(String id);

    boolean checkLogin(String login);

    void save(User user);

    @Override
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
}
