package ru.karelin.tmwebspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.repository.UserRepository;
import ru.karelin.tmwebspring.util.MD5Generator;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User findByLoginAndPassword(String login, String password){
        User user = userRepository.findByLogin(login);
        if (user!=null && user.getPassHash().equals(MD5Generator.generate(password))){
            return user;
        }
        return null;
    }
    public User find(String id){
        return userRepository.find(id);
    }

    public boolean checkLogin(String login) {
        return userRepository.findByLogin(login)!=null;
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
