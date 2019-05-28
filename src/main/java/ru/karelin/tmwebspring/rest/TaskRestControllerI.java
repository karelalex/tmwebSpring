package ru.karelin.tmwebspring.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.karelin.tmwebspring.dto.Result;
import ru.karelin.tmwebspring.dto.TaskDto;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface TaskRestControllerI {
    @GetMapping(value = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    TaskDto getTask(@PathVariable("id") String taskId, HttpSession session);

    @GetMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
    List<TaskDto> getTaskList(@RequestParam(value = "projectId", required = false, defaultValue = "") String projectId, HttpSession session);

    @PostMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Result createTask (@RequestBody TaskDto task, HttpSession session);

    @PutMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Result editTask(@RequestBody TaskDto task, HttpSession session);

    @DeleteMapping(value = "/task/{id}")
    Result removeTask(@PathVariable("id") String taskId, HttpSession session);
}
