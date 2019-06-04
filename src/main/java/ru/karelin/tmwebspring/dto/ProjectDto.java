package ru.karelin.tmwebspring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.karelin.tmwebspring.entity.AbstractEntity;
import ru.karelin.tmwebspring.enumeration.Status;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "project")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectDto extends AbstractEntity {
    private String name;

    private String description;

    @Column(name = "startingDate")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StdDateFormat.DATE_FORMAT_STR_ISO8601)
    private Date startingDate;

    @Column(name = "finishDate")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StdDateFormat.DATE_FORMAT_STR_ISO8601)
    private Date finishDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "user_id")
    private String userId;

}
