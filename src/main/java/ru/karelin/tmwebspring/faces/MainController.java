package ru.karelin.tmwebspring.faces;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@Component
@Scope("view")
public class MainController  {

    @Autowired
    private UserService userService;

    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

    private String login;
    private String password;

    private String userName;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService getUserService() {
        return userService;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        if (this.userName == null || this.userName.isEmpty()) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            User user = userService.find((String) session.getAttribute("userId"));
            if (user != null)
                this.userName = user.getUserName();
        }
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}