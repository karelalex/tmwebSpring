package ru.karelin.tmwebspring.faces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.repository.UserRepository;
import ru.karelin.tmwebspring.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Component
@ManagedBean
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class DateController {

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;


    public String getUserName(){
        String userId = (String) session.getAttribute("userId");
        System.out.println("pizda");
        if(userId!=null) {
            User user = userService.find(userId);
            return user.getUserName();
        }
        System.out.println("govno");
        return null;
    }
}
