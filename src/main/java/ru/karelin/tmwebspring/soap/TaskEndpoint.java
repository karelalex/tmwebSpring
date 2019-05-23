package ru.karelin.tmwebspring.soap;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;
import ru.karelin.tmwebspring.dto.TaskDto;
import ru.karelin.tmwebspring.service.TaskDtoService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebService
public class    TaskEndpoint {

    @Autowired
    TaskDtoService taskDtoService;

    @WebMethod
    public List<TaskDto> getTaskList(){
        return taskDtoService.findAllByUserId(getCurrentUserId());
    }

    @WebMethod
    public List<TaskDto> getTaskListByProjectId(
            @WebParam(name="projectId") String projectId
    ){
        return taskDtoService.findAllByUserIdAndProjectId(getCurrentUserId(), projectId);
    }

    @WebMethod
    public TaskDto getTaskById(
            @WebParam(name = "taskId") String id){
        return taskDtoService.findByIdAndUserId(id, getCurrentUserId());
    }

    @WebMethod
    public void removeTaskById(
            @WebParam(name = "taskId") String id){
        taskDtoService.remove(id, getCurrentUserId());
    }

    @WebMethod
    public void updateTask(
            @WebParam(name="task") TaskDto task
    ){
        taskDtoService.save(task);
    }


    private String getCurrentUserId() {
        Message message = PhaseInterceptorChain.getCurrentMessage();
        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
        HttpSession session = request.getSession(true);
        return (String)session.getAttribute("userId");
    }

}
