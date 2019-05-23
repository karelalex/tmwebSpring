package ru.karelin.tmwebspring.dto;

import lombok.Getter;
import lombok.Setter;
import ru.karelin.tmwebspring.entity.AbstractEntity;
import ru.karelin.tmwebspring.enumeration.Status;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "task")
public class TaskDto extends AbstractEntity {
    private String name;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date startingDate;

    @Temporal(TemporalType.DATE)
    private Date finishDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "project_id")
    private String  projectId;

    @Column(name = "user_id")
    private String userId;
}
