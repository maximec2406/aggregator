package ru.levelp.myapp.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan("ru.levelp.myapp") // сканит пакеты и создает компоненты
public class TestConfig {
    @Bean
    public EntityManagerFactory getEntityMangerFactory(){
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

    @Bean
    public EntityManager getEntityManager(EntityManagerFactory emf){
        return emf.createEntityManager();
    }


}
