package ru.karelin.tmwebspring.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.karelin.tmwebspring.dto.Result;

import javax.servlet.http.HttpSession;

public interface LoginRestControllerI {
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    Result login(@RequestParam(name = "login") String login,
                 @RequestParam(name = "password") String password, HttpSession session);

    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    Result logout(HttpSession session);
}
