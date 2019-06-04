package ru.karelin.tmwebspring.rest;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.karelin.tmwebspring.dto.Result;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginRestController implements LoginRestControllerI {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result login(@RequestParam(name = "login") String login,
                        @RequestParam(name = "password") String password, HttpSession session, HttpServletRequest httpServletRequest) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            @Nullable final User user = userService.findByLogin(authentication.getName());
            if (user != null) {
                httpServletRequest.getSession(true).setAttribute("userId", user.getId());
            }
            return new Result(authentication.isAuthenticated());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
    }
    @GetMapping ("/hello")
    public String sayHello(){
        return "Welcome to rest controller";
    }

    @Override
    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result logout(){
        try{
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        catch (Exception e){
            return new Result(false, e.getMessage());
        }
        return new Result();
    }

}
