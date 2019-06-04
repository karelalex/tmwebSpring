package ru.karelin.tmwebspring.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends AbstractEntity {

    @Column(unique = true)
    private String login;

    @Column(name = "passHash")
    private String passHash;

    @Column(name = "username")
    private String userName;
    @ManyToMany
    @JoinTable(name = "user_info_role",
            joinColumns = @JoinColumn(name = "User_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private List<Role> roles = new ArrayList<>();
}
