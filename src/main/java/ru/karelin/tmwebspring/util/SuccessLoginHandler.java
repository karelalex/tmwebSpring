package ru.karelin.tmwebspring.util;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessLoginHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        final String login = authentication.getName();
        System.out.println(login);
        @Nullable final User user = userService.findByLogin(login);
        if (user!=null){
            httpServletRequest.getSession(true).setAttribute("userId", user.getId());
            System.out.println("Установил!!!");
        }
        httpServletResponse.sendRedirect(httpServletRequest.getHeader("referer"));
    }
}
