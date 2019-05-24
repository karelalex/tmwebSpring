package ru.karelin.tmwebspring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.karelin.tmwebspring.dto.Result;
import ru.karelin.tmwebspring.dto.TaskDto;
import ru.karelin.tmwebspring.service.TaskDtoService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class TaskRestController {

    @Autowired
    TaskDtoService taskDtoService;


    @GetMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDto> getTaskList(HttpSession session){
        return taskDtoService.findAllByUserId((String)session.getAttribute("userId"));
    }

    @GetMapping(value = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto getTask(@PathVariable("id") String taskId,  HttpSession session){
        return taskDtoService.findByIdAndUserId(taskId, (String)session.getAttribute("userId"));
    }

    @GetMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDto> getTaskList(@RequestParam("projectId") String projectId, HttpSession session){
        return taskDtoService.findAllByUserIdAndProjectId(((String)session.getAttribute("userId")), projectId);
    }

    @PostMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result createTask (@RequestBody TaskDto task, HttpSession session)
    {
        String taskUserId = task.getUserId();
        if(taskUserId==null || taskUserId.isEmpty()){
            task.setUserId((String)session.getAttribute("userId"));
        }
        taskDtoService.save(task);
        return new Result();
    }
    @PutMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result editTask (@RequestBody TaskDto task, HttpSession session)
    {
        taskDtoService.save(task);
        return new Result();
    }

    @DeleteMapping(value = "task/{id}")
    public Result removeTask(@PathVariable("id") String taskId, HttpSession session)
    {
        return new Result(taskDtoService.remove(taskId, (String)session.getAttribute("userId")));
    }
}
