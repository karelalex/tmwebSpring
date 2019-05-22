package ru.karelin.tmwebspring.soap;

import org.springframework.beans.factory.annotation.Autowired;
import ru.karelin.tmwebspring.service.UserService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class LoginEndpoint {

    @Autowired
    private UserService userService;

    @WebMethod
    public boolean singIn(
            @WebParam(name = "login") String login,
            @WebParam (name = "password") String password
    ){
        return userService.findByLoginAndPassword(login, password) != null;
    }

}
