package ru.karelin.tmwebspring.config;


import com.hazelcast.config.Config;
import com.hazelcast.config.MapAttributeConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.hazelcast.HazelcastSessionRepository;
import org.springframework.session.hazelcast.PrincipalNameExtractor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@PropertySource({"classpath:hidden.properties"})
@EnableTransactionManagement

@EnableJpaRepositories(basePackages = "ru.karelin.tmwebspring.repository")
@Configuration()
public class MainConfig {
    @Bean
    public HazelcastInstance hazelcastInstance() {
        MapAttributeConfig attributeConfig = new MapAttributeConfig()
                .setName("id")
                .setExtractor(PrincipalNameExtractor.class.getName());

        Config config = new Config();

        config.getMapConfig(HazelcastSessionRepository.DEFAULT_SESSION_MAP_NAME)
                .addMapAttributeConfig(attributeConfig)
                .addMapIndexConfig(new MapIndexConfig(
                        "id", false));

        return Hazelcast.newHazelcastInstance(config);
    }
}
