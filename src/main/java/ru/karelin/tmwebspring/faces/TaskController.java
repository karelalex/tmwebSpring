package ru.karelin.tmwebspring.faces;

import ru.karelin.tmwebspring.entity.Project;
import ru.karelin.tmwebspring.entity.Task;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.enumeration.Status;
import ru.karelin.tmwebspring.service.ProjectService;
import ru.karelin.tmwebspring.service.TaskService;
import ru.karelin.tmwebspring.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class TaskController {
    @ManagedProperty("#{taskService}")
    private TaskService taskService;

    @ManagedProperty("#{userService}")
    private UserService userService;

    @ManagedProperty("#{projectService}")
    private ProjectService projectService;

    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    private List<Project> projectList;

    private List<Task> taskList;

    private Task currentTask;

    private String projectId;

    private String taskId;

    private boolean isCreating = false;

    public void initTaskList() {
        System.out.println(projectId);
        if (projectId != null && !projectId.isEmpty()) {
            taskList = taskService.findAllByUserIdAndProjectId((String) session.getAttribute("userId"), projectId);
        } else {
            taskList = taskService.findAllByUserId((String) session.getAttribute("userId"));
        }
    }

    public void initCurrentTask() throws IOException {
        if (taskId!=null)
            currentTask = taskService.findByIdAndUserId(taskId, (String) session.getAttribute("userId"));

        if (currentTask == null) {
            User user = userService.find((String) session.getAttribute("userId"));
            if (user == null) ((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()).sendError(HttpServletResponse.SC_FORBIDDEN, "Вы не залогинены");
            currentTask = new Task();
            currentTask.setUser(user);
            currentTask.setStartingDate(new Date());
            currentTask.setFinishDate(new Date());
            currentTask.setStatus(Status.PLANNED);
            if (projectId != null)
                currentTask.setProject(projectService.findByIdAndUserId(projectId, (String) session.getAttribute("userId")));
            isCreating=true;
        }
    }

    public String removeTask() {
        if (currentTask == null) return "taskList";
        taskService.remove(currentTask.getId(), (String) session.getAttribute("userId"));
        initTaskList();
        currentTask = null;
        return "taskList";
    }

    public void saveTask() {
        taskService.save(currentTask);
        isCreating = false;
    }

    public void initProjectList() {
        projectList = projectService.findAllByUserId((String) session.getAttribute("userId"));
    }


    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
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

    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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
        if (isCreating) {
            if (projectList==null || projectList.size()==0) {
                initProjectList();
            }
        }
    }

    public Status[] getStatuses() {
        return Status.values();
    }
}
