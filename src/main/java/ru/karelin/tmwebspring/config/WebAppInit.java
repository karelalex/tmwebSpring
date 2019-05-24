package ru.karelin.tmwebspring.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
       final AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
       ctx.register(WebConfig.class);
       ctx.setServletContext(servletContext);
       final DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", dispatcherServlet);
        dynamic.addMapping("/rest/*");
        dynamic.setLoadOnStartup(1);
    }
}
