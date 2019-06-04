package ru.karelin.tmwebspring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmwebspring.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("loginValidator")
@Component
@RequestScoped
public class UserLoginValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String login = (String) o;
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

}
