package ru.karelin.tmwebspring.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import ru.karelin.tmwebspring.enumeration.Status;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project extends AbstractEntity {

    private String name;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date startingDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date finishDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "project")
    List<Task> tasks = new ArrayList<>();

    @ManyToOne
    private User user;
}
