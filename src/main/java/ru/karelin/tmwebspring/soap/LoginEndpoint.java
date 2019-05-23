package ru.karelin.tmwebspring.soap;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.service.UserService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebService
public class LoginEndpoint {

    @Autowired
    private UserService userService;

    @WebMethod
    public String singIn(
            @WebParam(name = "login") String login,
            @WebParam(name = "password") String password
    ) {
        User user = userService.findByLoginAndPassword(login, password);
        if (user != null) {
            Message message = PhaseInterceptorChain.getCurrentMessage();
            HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.getId());
            return user.getId();
        } else return null;
    }

}
