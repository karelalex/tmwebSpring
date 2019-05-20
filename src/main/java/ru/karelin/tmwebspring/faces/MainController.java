package ru.karelin.tmwebspring.faces;

import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.find(userId);
            return user.getUserName();
        }
        return null;
    }
}