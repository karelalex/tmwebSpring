package ru.karelin.tmwebspring.validator;

import ru.karelin.tmwebspring.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("loginValidator")
@ManagedBean(name = "userLoginValidator")
@RequestScoped
public class UserLoginValidator implements Validator {

    @ManagedProperty("#{userService}")
    private UserService userService;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String login = (String) o;
        System.out.println("Добрый день");
        if (login == null || login.trim().isEmpty()) {
            FacesMessage msg = new FacesMessage("Это поле обязательно для заполнения");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        if (login.trim().length() < 2) {
            FacesMessage msg = new FacesMessage("Выберете более длинный логин");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        if (userService.checkLogin(login)) {
            FacesMessage msg = new FacesMessage("Такой логин уже существует, выберете другой");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
