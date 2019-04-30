package ru.karelin.tmwebspring.entity;

import lombok.Getter;
import lombok.Setter;
import ru.karelin.tmwebspring.enumeration.Status;

import java.util.Date;

@Getter
@Setter
public class Task extends AbstractEntity{
    private String name;
    private String description;
    private Date startingDate;
    private Date finishDate;
    private Status status;
    private String projectId;
    private String userId;
}
