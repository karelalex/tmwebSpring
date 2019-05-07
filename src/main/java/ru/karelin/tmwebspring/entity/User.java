package ru.karelin.tmwebspring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user_info")
public class User extends AbstractEntity {
    @Column(unique = true)
    private String login;
    private String passHash;
    private String username;
}
