package ru.karelin.tmwebspring.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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

    @Override
    public String toString() {
        return "Project{" +
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

        if (!name.equals(project.name)) return false;
        if (description != null ? !description.equals(project.description) : project.description != null) return false;
        if (!startingDate.equals(project.startingDate)) return false;
        if (!finishDate.equals(project.finishDate)) return false;
        if (status != project.status) return false;
        return user.getId().equals(project.user.getId());

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + startingDate.hashCode();
        result = 31 * result + finishDate.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + user.getId().hashCode();
        return result;
    }
}
