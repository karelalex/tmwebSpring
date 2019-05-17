package ru.karelin.tmwebspring.faces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@ManagedBean
@ViewScoped
public class MainController {

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

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

    public String login(){
        User user = userService.findByLoginAndPassword(login, password);
        System.out.println(login);
        if (user != null) {
            System.out.println("охуеть");
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("userId", user.getId());
            return "main";
        } else {
            return "main";
        }
    }
    public String logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "main";
    }
}
