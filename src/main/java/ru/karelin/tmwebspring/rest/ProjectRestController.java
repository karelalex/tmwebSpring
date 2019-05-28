package ru.karelin.tmwebspring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.karelin.tmwebspring.dto.ProjectDto;
import ru.karelin.tmwebspring.dto.Result;
import ru.karelin.tmwebspring.service.ProjectDtoService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ProjectRestController implements ProjectRestControllerI {

    @Autowired
    ProjectDtoService projectDtoService;


    @Override
    @GetMapping(value = "/project", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectDto> getProjectList(HttpSession session){
        return projectDtoService.findAllByUserId((String)session.getAttribute("userId"));
    }

    @Override
    @GetMapping(value = "/project/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDto getProject(@PathVariable("id") String projectId, HttpSession session){
        return projectDtoService.findByIdAndUserId(projectId, (String)session.getAttribute("userId"));
    }

    @Override
    @PostMapping(value = "/project", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result createProject(@RequestBody ProjectDto project, HttpSession session)
    {
        try {
            String projectUserId = project.getUserId();
            if (projectUserId == null || projectUserId.isEmpty()) {
                project.setUserId((String) session.getAttribute("userId"));
            }
            projectDtoService.save(project);
        }
        catch (Exception e){
            return new Result(false, e.getMessage());
        }
        return new Result();
    }
    @Override
    @PutMapping(value = "/project", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result editProject(@RequestBody ProjectDto project, HttpSession session)
    {
        projectDtoService.save(project);
        return new Result();
    }

    @Override
    @DeleteMapping(value = "project/{id}")
    public Result removeProject(@PathVariable("id") String projectId, HttpSession session)
    {
        return new Result(projectDtoService.remove(projectId, (String)session.getAttribute("userId")));
    }
}
