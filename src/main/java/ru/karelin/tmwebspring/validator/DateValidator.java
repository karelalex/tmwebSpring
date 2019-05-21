package ru.karelin.tmwebspring.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;

@FacesValidator("DateValidator")
public class DateValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        UIInput startDateInput = (UIInput) facesContext.getViewRoot().findComponent("itemForm:startDate");
        Date startDate = (Date) startDateInput.getValue();
        Date finnishDate = (Date) o;
        if (startDate.after(finnishDate)) {
            FacesMessage msg = new FacesMessage("Дата завершения не может быть раньше даты начала");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
