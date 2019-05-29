package ru.karelin.tmwebspring.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.karelin.tmwebspring.dto.ProjectDto;
import ru.karelin.tmwebspring.dto.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ProjectRestControllerI {
    @GetMapping(value = "/project", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProjectDto> getProjectList(HttpSession session);

    @GetMapping(value = "/project/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProjectDto getProject(@PathVariable("id") String projectId, HttpSession session);

    @PostMapping(value = "/project", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Result createProject (@RequestBody ProjectDto project, HttpSession session);

    @PutMapping(value = "/project", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Result editProject(@RequestBody ProjectDto project, HttpSession session);

    @DeleteMapping(value = "project/{id}")
    Result removeProject(@PathVariable String projectId, HttpSession session);
}
