package ru.karelin.tmwebspring.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Role extends AbstractEntity {
    private String name;
}
