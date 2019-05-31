package ru.karelin.tmwebspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.karelin.tmwebspring.service.UserService;
import ru.karelin.tmwebspring.util.SuccessLoginHandler;

@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    SuccessLoginHandler successLoginHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // require all requests to be authenticated except for the resources
        http.authorizeRequests().antMatchers("/javax.faces.resource/**")
                .permitAll().anyRequest().authenticated();
        // login
        http.formLogin().loginPage("/").permitAll()
                .failureUrl("/?error=true");
        http.formLogin().loginProcessingUrl("/singIn").successHandler(successLoginHandler).permitAll();
        // logout
        http.logout().logoutUrl("/singOut").logoutSuccessUrl("/");
        // not needed as JSF 2.2 is implicitly protected against CSRF
        http.csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}

