package ru.karelin.tmwebspring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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


@Component
@RequestScoped
public class ProjectConverter implements Converter {

    @Autowired
    private HttpSession session;


    @Autowired
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
