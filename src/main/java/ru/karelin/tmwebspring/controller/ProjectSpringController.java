package ru.karelin.tmwebspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.karelin.tmwebspring.entity.Project;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.enumeration.Status;
import ru.karelin.tmwebspring.service.ProjectService;
import ru.karelin.tmwebspring.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectSpringController {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String projectId, HttpSession session, Model model) {
        User currentUser = userService.find((String) session.getAttribute("userId"));
        model.addAttribute("user", currentUser);
        Project project = projectService.findByIdAndUserId(projectId, currentUser.getId());
        model.addAttribute("project", project);
        return "edit-project";
    }

    @GetMapping("/show")
    public String showProjectList(HttpSession session, Model model) {
        User currentUser = userService.find((String) session.getAttribute("userId"));
        model.addAttribute("user", currentUser);
        List<Project> projects = projectService.findAllByUserId(currentUser.getId());
        model.addAttribute("projects", projects);
        return "project-list";

    }

    @GetMapping("/show/{id}")
    public String showProject(@PathVariable("id") String projectId,  HttpSession session, Model model) {
        User currentUser = userService.find((String) session.getAttribute("userId"));
        model.addAttribute("user", currentUser);
        Project project = projectService.findByIdAndUserId(projectId, currentUser.getId());
        model.addAttribute("project", project);
        return "show-project";

    }
    @PostMapping("/edit")
    public String editProject(@ModelAttribute("project") Project project, HttpSession session) {
        User currentUser = userService.find((String) session.getAttribute("userId"));
        project.setUser(currentUser);
        projectService.save(project);
        return "redirect:/project/show/"+project.getId();
    }

    @GetMapping("/remove/{pid}")
    public String removeProject(@PathVariable("pid") String projectId, HttpSession session){
        projectService.remove(projectId, (String) session.getAttribute("userId"));
        return "redirect:/project/show";
    }

    @GetMapping("/create")
    public String showCreateProjectForm(Model model, HttpSession session){
        User currentUser = userService.find((String) session.getAttribute("userId"));
        model.addAttribute("user", currentUser);
        Project project = new Project();
        project.setStartingDate(new Date());
        project.setFinishDate(new Date());
        model.addAttribute("project", project);
        return "create-project";
    }
    @PostMapping("/create")
    public String createProject(@ModelAttribute("project") Project project, HttpSession session) {
        User currentUser = userService.find((String) session.getAttribute("userId"));
        project.setStatus(Status.PLANNED);
        project.setUser(currentUser);
        projectService.save(project);
        return "redirect:/project/show/"+project.getId();
    }
}
