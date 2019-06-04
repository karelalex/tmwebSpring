package ru.karelin.tmwebspring.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import ru.karelin.tmwebspring.enumeration.Status;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task extends AbstractEntity {

    private String name;

    private String description;

    @Column(name = "startingDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date startingDate;

    @Column(name = "finishDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date finishDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Project project;

    @ManyToOne
    //@JoinColumn
    private User user;
}
