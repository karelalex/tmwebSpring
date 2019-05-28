package ru.karelin.tmwebspring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.karelin.tmwebspring.dto.Result;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;

import javax.servlet.http.HttpSession;

@RestController
public class LoginRestController implements LoginRestControllerI {

    @Autowired
    UserService userService;

    @Override
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result login(@RequestParam(name = "login") String login,
                        @RequestParam(name = "password") String password, HttpSession session) {
        User user = userService.findByLoginAndPassword(login, password);
        if (user!=null) {
            session.setAttribute("userId", user.getId());
            return new Result();
        }
       return new Result(false);
    }
    @GetMapping ("/hello")
    public String sayHello(){
        return "Welcome to rest controller";
    }

    @Override
    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result logout(HttpSession session){
        session.invalidate();
        return new Result();
    }

}
