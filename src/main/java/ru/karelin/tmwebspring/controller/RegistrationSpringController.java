package ru.karelin.tmwebspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.karelin.tmwebspring.dto.UserRegDto;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;
import ru.karelin.tmwebspring.util.MD5Generator;

@Controller
public class RegistrationSpringController {

    @Autowired
    @Qualifier("userDtoValidator")
    Validator validator;

    @Autowired
    UserService userService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @GetMapping("/reg")
    public ModelAndView showRegForm(){
        ModelAndView modelAndView = new ModelAndView("reg-form");
        modelAndView.addObject("userDto", new UserRegDto());
        return modelAndView;
    }

    @PostMapping ("/reg")
    public String registerUser(@ModelAttribute("userDto") @Validated UserRegDto userRegDto, BindingResult bindingResult, Model model){
        if(userService.checkLogin(userRegDto.getLogin())) {
            bindingResult.rejectValue("login", "login.exists", "Такой логин уже существует");
        }
        if (bindingResult.hasErrors()){
            return "reg-form";
        }
        else {
            User user = new User();
            user.setLogin(userRegDto.getLogin());
            user.setPassHash(MD5Generator.generate(userRegDto.getPassword()));
            user.setUserName(userRegDto.getUserName());
            userService.save(user);
            return "/login-form";
        }
    }


}
