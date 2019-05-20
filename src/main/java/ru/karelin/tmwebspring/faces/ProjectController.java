package ru.karelin.tmwebspring.faces;

import ru.karelin.tmwebspring.entity.Project;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.enumeration.Status;
import ru.karelin.tmwebspring.service.ProjectService;
import ru.karelin.tmwebspring.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class ProjectController {
    @ManagedProperty("#{projectService}")
    private ProjectService projectService;

    @ManagedProperty("#{userService}")
    private UserService userService;

    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    private List<Project> projectList;

    private Project currentProject;

    private String projectId;

    private boolean isCreating = false;

    public void initProjectList() {
        projectList = projectService.findAllByUserId((String) session.getAttribute("userId"));
    }

    public void initCurrentProject() {
        if (projectId != null)
            currentProject = projectService.findByIdAndUserId(projectId, (String) session.getAttribute("userId"));
        if (currentProject == null && isCreating) {
            User user = userService.find((String) session.getAttribute("userId"));
            if (user == null) throw new IllegalStateException("Вы не вошли в систему");
            currentProject = new Project();
            currentProject.setUser(user);
            currentProject.setStartingDate(new Date());
            currentProject.setFinishDate(new Date());
            currentProject.setStatus(Status.PLANNED);
        }
    }

    public String removeProject() {
        if (currentProject == null) return "projectList";
        projectService.remove(currentProject.getId(), (String) session.getAttribute("userId"));
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
