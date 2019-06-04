package ru.karelin.tmwebspring.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.karelin.tmwebspring.soap.LoginEndpoint;
import ru.karelin.tmwebspring.soap.ProjectEndpoint;
import ru.karelin.tmwebspring.soap.TaskEndpoint;

import javax.xml.ws.Endpoint;

@Configuration
public class WSConfig {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint loginEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new LoginEndpoint());
        endpoint.publish("/login");
        return endpoint;
    }

    @Bean
    public Endpoint projectEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new ProjectEndpoint());
        endpoint.publish("/project");
        return endpoint;
    }

    @Bean
    public Endpoint taskEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new TaskEndpoint());
        endpoint.publish("/task");
        return endpoint;
    }
}

