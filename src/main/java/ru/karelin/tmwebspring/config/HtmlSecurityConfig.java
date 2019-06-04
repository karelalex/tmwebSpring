package ru.karelin.tmwebspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.karelin.tmwebspring.util.SuccessLoginHandler;


@Configuration
public class HtmlSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SuccessLoginHandler successLoginHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // require all requests to be authenticated except for the resources
        http.authorizeRequests().antMatchers("/javax.faces.resource/**").permitAll()
                .antMatchers("/reg").permitAll()
                .antMatchers("/index.xhtml").permitAll()
                .anyRequest().authenticated();
        // login
        http.formLogin().loginPage("/").permitAll()
                .failureUrl("/?error=true");
        http.formLogin().loginProcessingUrl("/singIn").successHandler(successLoginHandler).permitAll();
        // logout
        http.logout().logoutUrl("/singOut").logoutSuccessUrl("/");

        // not needed as JSF 2.2 is implicitly protected against CSRF
        http.csrf().disable();


    }
}



