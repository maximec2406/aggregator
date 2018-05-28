package ru.levelp.myapp.model;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelp.myapp.conf.TestConfig;
import ru.levelp.myapp.dao.PartsDAO;

import javax.persistence.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ru.levelp.myapp.model.Part.SEARCH_BY_PART_ID;

//import static org.

/**
 * Created by ulbas on 06.05.2018.
 */

@RunWith(SpringJUnit4ClassRunner.class) // сообщаем junit, что этот класс используется со spring
@ContextConfiguration(classes = TestConfig.class) // указываем, какую конфигурацию юзать
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //жизненный цикл контекста, указывает, пересоздавать ли контекст при/до/после вызова метода
// здесь задано пересоздавать контекст при вызове каждого нового теста
public class PartTest {

    private EntityManagerFactory emf;

    @Autowired
    private EntityManager em;

    @Autowired
    private PartsDAO dao;

    private static final Logger log = Logger.getLogger(PartTest.class);


    @Before
    public void setup() {
        emf = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = emf.createEntityManager();
        dao = new PartsDAO(em);
        log.info("setup before");
    }

    @After
    public void end(){
        // обязательно нужно закрывать, иначе будет утечка данных, уничтожай ресурсы
        em.close();
        emf.close();
    }

    @Test
    public void testCreatePart() throws Throwable {

       Supplier supplier = dao.createSupplier("производитель");
       Part part = dao.createPart("0001", "деталь", supplier);
        em.getTransaction().begin();
        try {
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            log.error("исключение в testCreatePart");
            throw t;
        }
        Part found = em.find(Part.class, part.getId());
        assertNotNull(found);
        assertNotNull(found.getSupplier());
    }

    @Test
    public  void testQuery() throws Throwable {
        testCreatePart();
        String searchString = "0001";

        @SuppressWarnings("unchecked") // игнорируем вариниг
        List<Part> parts = dao.findById(searchString);

        assertEquals(1, parts.size()); // найдена только одна деталь
        Part found = parts.get(0);
        assertEquals(searchString, found.getPartId()); // найдена именна деталь с нужным id

    }
}
