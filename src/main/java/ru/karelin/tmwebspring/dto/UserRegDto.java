package ru.karelin.tmwebspring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegDto {
    private String login;
    private String password;
    private String passwordRepeat;
    private String userName;
}
