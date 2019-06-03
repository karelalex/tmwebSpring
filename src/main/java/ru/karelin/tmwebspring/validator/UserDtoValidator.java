package ru.karelin.tmwebspring.validator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.karelin.tmwebspring.dto.UserRegDto;

@Component
@Qualifier("userDtoValidator")
public class UserDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegDto user = (UserRegDto) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.required", "Логин не должне быть пустым");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameOfUser", "userName.required", "Требуется ввести имя пользователя");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "Пароль не может быть пустым");
        if (!user.getPassword().equals(user.getPasswordRepeat())) {
            errors.rejectValue("passwordRepeat", "notMatch", "Пароли должны совпадать");
        }
    }
}
