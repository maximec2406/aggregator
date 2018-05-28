package ru.levelp.myapp.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@EnableTransactionManagement //включаем транзактион манагер
@Configuration
@ComponentScan("ru.levelp.myapp") // сканит пакеты и создает компоненты
@EnableWebMvc
public class ProdConfig {

    @Bean
    public EntityManagerFactory getEntityMangerFactory(){
        return Persistence.createEntityManagerFactory("ProductionPersistenceUnit");
    }

    @Bean
    @PersistenceContext //иначе спринг не будет знать, что при уничтожении контекста надо закрыть em
    public EntityManager getEntityManager(EntityManagerFactory emf){
        return emf.createEntityManager();
    }

    //    по имени находит вьюху
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setSuffix(".jsp");
        resolver.setPrefix("/pages/");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }


//    @Bean
//    public PlatformTransactionManager getTransactionManager(){
//
//    }

}
