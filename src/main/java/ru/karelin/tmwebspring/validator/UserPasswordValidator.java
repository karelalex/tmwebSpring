package ru.karelin.tmwebspring.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("UserPasswordValidator")
public class UserPasswordValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        UIInput passwordField = (UIInput) facesContext.getViewRoot().findComponent("regForm:password");
        String password = (String) passwordField.getValue();
        String repeatPassword = (String) o;
        System.out.println("Пришла беда, отворяй ворота");
        System.out.println(password+repeatPassword);
        if (!password.equals(repeatPassword)) {
            FacesMessage msg = new FacesMessage("Пароли должны совпадать");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            System.out.println("соси хуи");
            throw new ValidatorException(msg);
        }
        if (repeatPassword.trim().length()<8) {
            FacesMessage msg = new FacesMessage("Введите более длинный пароль");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            System.out.println("пизда во рту");
            throw new ValidatorException(msg);
        }
    }
}