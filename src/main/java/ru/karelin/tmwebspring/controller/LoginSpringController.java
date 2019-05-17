package ru.karelin.tmwebspring.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginSpringController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login-form";
    }

    @PostMapping("/login")
    public String login(@RequestParam("login") String login, @RequestParam("pass") String password, HttpSession session) {
        User user = userService.findByLoginAndPassword(login, password);
        if (user != null) {
            session.setAttribute("userId", user.getId());
            return "redirect:/";
        } else {
            return "login-form";
        }
    }

    @GetMapping("/logout")
    public String logout(@NotNull HttpSession session){
        session.invalidate();
        return ("redirect:/");
    }
}

