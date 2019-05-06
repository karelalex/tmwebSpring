package ru.karelin.tmwebspring.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.karelin.tmwebspring.enumeration.Status;

import java.util.Date;

@Getter
@Setter
public class Project extends AbstractEntity {
    private String name;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startingDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finishDate;
    private Status status;
    private String userId;
}
