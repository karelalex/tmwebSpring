package ru.karelin.tmwebspring.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@PropertySource({"classpath:hidden.properties"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.karelin.tmwebspring.repository")
@Configuration()
public class MainConfig  {


}
