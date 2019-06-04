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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "project")
    List<Task> tasks = new ArrayList<>();

    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "Project{" +
                "id='" + this.getId() + '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startingDate=" + startingDate +
                ", finishDate=" + finishDate +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return this.getId().equals(project.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
