package ru.karelin.tmwebspring.faces;

import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.Random;


@ManagedBean
@ViewScoped
public class MainController {

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    private String login;
    private String password;

    private String userName;

    public void setUserService(UserService userService) {
        this.userService = userService;
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

    public String login() {
        User user = userService.findByLoginAndPassword(login, password);
        if (user != null) {
            userName = user.getUserName();
            session.setAttribute("userId", user.getId());
            return "main";
        } else {
            return "main";
        }
    }

    public String logout() {
        session.invalidate();
        return "main";
    }

    public String getUserName() {
        User user = userService.find((String)session.getAttribute("userId"));
        if(user==null) return null;
        return user.getUserName();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}