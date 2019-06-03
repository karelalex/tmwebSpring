package ru.karelin.tmwebspring.faces;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.karelin.tmwebspring.dto.UserRegDto;
import ru.karelin.tmwebspring.entity.Role;
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

    @ManagedProperty("#{userServiceImpl}")
    private UserService userService;

    @ManagedProperty("#{passwordEncoder}")
    private PasswordEncoder passwordEncoder;

    private UserRegDto userRegDto = new UserRegDto();

    public String register() throws IOException {
        User user = new User();
        user.setLogin(userRegDto.getLogin());
        user.setPassHash(passwordEncoder.encode(userRegDto.getPassword()));
        user.setUserName(userRegDto.getUserName());
        user.getRoles().add(new Role("ROLE_USER"));
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

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
