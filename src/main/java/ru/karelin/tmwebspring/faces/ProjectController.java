package ru.karelin.tmwebspring.faces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.karelin.tmwebspring.entity.Project;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.enumeration.Status;
import ru.karelin.tmwebspring.service.ProjectService;
import ru.karelin.tmwebspring.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Access;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
@Scope("view")
public class ProjectController {
    @Autowired
   private ProjectService projectService;

    @Autowired
  private UserService userService;

    private List<Project> projectList;

    private Project currentProject;

    private String projectId;

    private boolean isCreating = false;

    public void initProjectList() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String userId = (String) session.getAttribute("userId");
        projectList = projectService.findAllByUserId(userId);
    }

    public String initCurrentProject() throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String userId = (String) session.getAttribute("userId");
        if (projectId != null) {
            currentProject = projectService.findByIdAndUserId(projectId, userId);
            if (currentProject==null) return "pretty:projectList";
        }
        if (currentProject == null) {
            User user = userService.find(userId);
            if (user == null) ((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()).sendError(HttpServletResponse.SC_FORBIDDEN, "Вы не залогинены");
            currentProject = new Project();
            currentProject.setUser(user);
            currentProject.setStartingDate(new Date());
            currentProject.setFinishDate(new Date());
            currentProject.setStatus(Status.PLANNED);
            isCreating = true;
        }
        return null;
    }

    public String removeProject() {
        if (currentProject == null) return "projectList";
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String userId = (String) session.getAttribute("userId");
        projectService.remove(currentProject.getId(), userId);
        initProjectList();
        currentProject=null;
        return "projectList";
    }

    public void saveProject() {
        projectService.save(currentProject);
        isCreating = false;
    }


    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public boolean isCreating() {
        return isCreating;
    }

    public void setCreating(boolean creating) {
        isCreating = creating;
    }

    public Status[] getStatuses() {
        return Status.values();
    }
}
