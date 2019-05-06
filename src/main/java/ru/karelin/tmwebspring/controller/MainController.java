package ru.karelin.tmwebspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getWelcomePage(HttpSession session, Model model){
        final User currentUser = userService.find((String) session.getAttribute("userId"));
        model.addAttribute("user", currentUser);
        return "welcome-page";
    }


}
