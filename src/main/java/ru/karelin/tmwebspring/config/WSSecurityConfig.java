package ru.karelin.tmwebspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import ru.karelin.tmwebspring.service.UserService;
import ru.karelin.tmwebspring.util.SuccessLoginHandler;


@Configuration
@Order(2)
public class WSSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    SuccessLoginHandler successLoginHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //rest and soap
        http
                .authorizeRequests().antMatchers("/rest/login", "rest/hello").permitAll()
                .antMatchers(HttpMethod.GET, "/soap/*").permitAll()
                //.anyRequest().authenticated();
        .antMatchers("rest/*", "soap/*").authenticated()
                .and()
        .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)); //todo settle up with this



        http.csrf().disable();
    }




}

