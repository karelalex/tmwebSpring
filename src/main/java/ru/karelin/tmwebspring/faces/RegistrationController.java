package ru.karelin.tmwebspring.faces;

import ru.karelin.tmwebspring.dto.UserRegDto;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;
import ru.karelin.tmwebspring.util.MD5Generator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@ViewScoped
public class RegistrationController {

    @ManagedProperty("#{userService}")
    private UserService userService;

    private UserRegDto userRegDto = new UserRegDto();

    public String register() throws IOException {
        User user = new User();
        user.setLogin(userRegDto.getLogin());
        user.setPassHash(MD5Generator.generate(userRegDto.getPassword()));
        user.setUserName(userRegDto.getUserName());
        userService.save(user);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Успешно",  "Пользователь " + user.getUserName() + " успешно зарегистрирован") );
        return "main";
    }


    public UserRegDto getUserRegDto() {
        return userRegDto;
    }

    public void setUserRegDto(UserRegDto userRegDto) {
        this.userRegDto = userRegDto;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
