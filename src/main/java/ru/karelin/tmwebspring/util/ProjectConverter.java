package ru.karelin.tmwebspring.util;

import ru.karelin.tmwebspring.entity.Project;
import ru.karelin.tmwebspring.service.ProjectService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

@FacesConverter("projectConverter")
@ManagedBean(name = "projectConverter")
@RequestScoped
public class ProjectConverter implements Converter {

    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ManagedProperty("#{projectService}")
    private ProjectService projectService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return projectService.findByIdAndUserId(s, (String) session.getAttribute("userId"));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return ((Project) o).getId();
    }
}
