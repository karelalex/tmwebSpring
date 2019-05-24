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
public class ProjectRestController {

    @Autowired
    ProjectDtoService projectDtoService;


    @GetMapping(value = "/project", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectDto> getProjectList(HttpSession session){
        return projectDtoService.findAllByUserId((String)session.getAttribute("userId"));
    }

    @GetMapping(value = "/project/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectDto getProject(@PathVariable("id") String projectId,  HttpSession session){
        return projectDtoService.findByIdAndUserId(projectId, (String)session.getAttribute("userId"));
    }

    @PostMapping(value = "/project", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result createProject (@RequestBody ProjectDto project, HttpSession session)
    {
        String projectUserId = project.getUserId();
        if(projectUserId==null || projectUserId.isEmpty()){
            project.setUserId((String)session.getAttribute("userId"));
        }
        projectDtoService.save(project);
        return new Result();
    }
    @PutMapping(value = "/project", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result editProject (@RequestBody ProjectDto project, HttpSession session)
    {
        projectDtoService.save(project);
        return new Result();
    }

    @DeleteMapping(value = "project/{id}")
    public Result removeProject(@PathVariable("id") String projectId, HttpSession session)
    {
        return new Result(projectDtoService.remove(projectId, (String)session.getAttribute("userId")));
    }
}
