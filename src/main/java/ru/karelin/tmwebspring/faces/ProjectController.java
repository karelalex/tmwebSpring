package ru.karelin.tmwebspring.faces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmwebspring.service.ProjectService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProjectController {
    @ManagedProperty("#{projectService}")
    private ProjectService projectService;
}
