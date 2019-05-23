package ru.karelin.tmwebspring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:application.properties", "classpath:hidden.properties"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.karelin.tmwebspring.repository")
public class PersistenceConfig {

    @Bean
    public DataSource dataSource(
            @Value("${db.driver}") final String driver,
            @Value("${db.url}") final String url,
            @Value("${db.user}") final String user,
            @Value("${db.password}") final String pass
    ) {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pass);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource,
                                                                       @Value("${hibernate.show_sql}") final boolean showSql,
                                                                       @Value("${hibernate.hbm2ddl.auto}") final String dbStrategy,
                                                                       @Value("${hibernate.dialect}") final String dialect,
                                                                       @Value("${hibernate.dialect.storage_engine}") final String engine) {
        final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("ru.karelin.tmwebspring.entity", "ru.karelin.tmwebspring.dto");
        final Properties properties = new Properties();
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.hbm2ddl.auto", dbStrategy);
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.dialect.storage_engine", engine);
        properties.put("hibernate.cache.use_second_level_cache", "true");
        properties.put("hibernate.cache.use_query_cache", "true");
        properties.put("hibernate.cache.use_minimal_puts", "true");
        properties.put("hibernate.cache.hazelcast.use_lite_member", "true");
        properties.put("hibernate.cache.region_prefix", "tm-spring");
//        properties.put("hibernate.cache.provider_configuration_file_resource_path", "hazelcast.xml");
       properties.put("hibernate.cache.region.factory_class", "com.hazelcast.hibernate.HazelcastLocalCacheRegionFactory");
        factoryBean.setJpaProperties(properties);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean){
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactoryBean.getObject());
        return transactionManager;
    }


}
