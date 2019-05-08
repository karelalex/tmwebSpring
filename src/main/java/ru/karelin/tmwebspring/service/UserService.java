package ru.karelin.tmwebspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.repository.UserRepository;
import ru.karelin.tmwebspring.util.MD5Generator;

@Service
@Transactional
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
        if(id==null) return null;
        return userRepository.findOneById(id);
    }

    public boolean checkLogin(String login) {
        return userRepository.findByLogin(login)!=null;
    }

    public void save(User user) {
        if(user==null) return;
        userRepository.save(user);
    }
}
