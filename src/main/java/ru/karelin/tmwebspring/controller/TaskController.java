package ru.karelin.tmwebspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.karelin.tmwebspring.entity.Project;
import ru.karelin.tmwebspring.entity.Task;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.enumeration.Status;
import ru.karelin.tmwebspring.service.ProjectService;
import ru.karelin.tmwebspring.service.TaskService;
import ru.karelin.tmwebspring.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RequestMapping("/task")
@Controller
public class TaskController {
    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/show")
    public String showFullTaskList(HttpSession session, Model model) {
        User currentUser = userService.find((String) session.getAttribute("userId"));
        model.addAttribute("user", currentUser);
        List<Task> tasks = taskService.findAllByUserId(currentUser.getId());
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/show/pid/{pid}")
    public String showTaskListByProjectId(@PathVariable("pid") String projectId, HttpSession session, Model model) {
        final User currentUser = userService.find((String) session.getAttribute("userId"));
        model.addAttribute("user", currentUser);
        final List<Task> tasks = taskService.findAllByUserIdAndProjectId(currentUser.getId(), projectId);
        model.addAttribute("tasks", tasks);
        model.addAttribute("pid", projectId);
        return "task-list";
    }

    @GetMapping("/show/{id}")
    public String showTask(@PathVariable("id") String taskId, HttpSession session, Model model) {
        final User currentUser = userService.find((String) session.getAttribute("userId"));
        model.addAttribute("user", currentUser);
        final Task task = taskService.findByIdAndUserId(taskId, currentUser.getId());
        model.addAttribute("task", task);
        return "show-task";
    }

    @GetMapping("/create")
    public String showTaskCreateForm(Model model, HttpSession session) {
        final User currentUser = userService.find((String) session.getAttribute("userId"));
        model.addAttribute("user", currentUser);
        Task task = new Task();
        task.setStartingDate(new Date());
        task.setFinishDate(new Date());
        model.addAttribute("task", task);
        final List<Project> projects = projectService.findAllByUserId(currentUser.getId());
        model.addAttribute("projects", projects);
        return "create-task";
    }

    @GetMapping("/create/{pid}")
    public String showTaskCreateForm(@PathVariable("pid") String projectId, Model model, HttpSession session) {
        final User currentUser = userService.find((String) session.getAttribute("userId"));
        model.addAttribute("user", currentUser);
        Task task = new Task();
        task.setStartingDate(new Date());
        task.setFinishDate(new Date());
        task.setProjectId(projectId);
        model.addAttribute("task", task);
        final List<Project> projects = projectService.findAllByUserId(currentUser.getId());
        model.addAttribute("projects", projects);
        return "create-task";
    }

    @PostMapping("/create")
    public String createProject(@ModelAttribute("task") Task task, HttpSession session) {
        User currentUser = userService.find((String) session.getAttribute("userId"));
        task.setStatus(Status.PLANNED);
        task.setUserId(currentUser.getId());
        taskService.save(task);
        return "redirect:/task/show/"+task.getId();
    }

}
