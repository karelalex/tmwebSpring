package ru.karelin.tmwebspring.faces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.karelin.tmwebspring.dto.UserRegDto;
import ru.karelin.tmwebspring.entity.Role;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.io.IOException;

@Component
@Scope("view")
public class RegistrationController {

    @Autowired
    private UserService userService;

    private UserRegDto userRegDto = new UserRegDto();

    public String register() throws IOException {
        userService.save(userRegDto);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Успешно", "Пользователь " + userRegDto.getUserName() + " успешно зарегистрирован"));
        return "main";
    }


    public UserRegDto getUserRegDto() {
        return userRegDto;
    }

    public void setUserRegDto(UserRegDto userRegDto) {
        this.userRegDto = userRegDto;
    }


}
