package ru.karelin.tmwebspring.faces;

import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;


@ManagedBean
@ViewScoped
public class MainController  {

    @ManagedProperty(value = "#{userServiceImpl}")
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