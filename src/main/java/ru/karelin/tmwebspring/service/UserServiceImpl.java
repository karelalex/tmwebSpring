package ru.karelin.tmwebspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karelin.tmwebspring.entity.Role;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.repository.UserRepository;
import ru.karelin.tmwebspring.util.MD5Generator;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findByLoginAndPassword(String login, String password){
        User user = userRepository.findByLogin(login);
        if (user!=null && user.getPassHash().equals(MD5Generator.generate(password))){
            return user;
        }
        return null;
    }

    @Override
    public User findByLogin(String login){
        if(login!=null && !login.isEmpty())
        return  userRepository.findByLogin(login);
        return null;
    }
    @Override
    public User find(String id){
        if(id==null) return null;
        return userRepository.findOneById(id);
    }

    @Override
    public boolean checkLogin(String login) {
        return userRepository.findByLogin(login)!=null;
    }

    @Override
    public void save(User user) {
        if(user==null) return;
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassHash(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
