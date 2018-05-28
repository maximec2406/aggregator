package ru.levelp.myapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levelp.myapp.model.Part;
import ru.levelp.myapp.model.Supplier;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static ru.levelp.myapp.model.Part.SEARCH_BY_PART_ID;

/**
 * Created by ulbas on 12.05.2018.
 */
@Component // spring при старте ищет все компоненты и создает их
public class PartsDAO {

    private final EntityManager em;

    public PartsDAO (@Autowired EntityManager em) { // указываем спрингу, что хотим получить параметр снаружи
        this.em = em;
    }

    @Transactional // генерирует кучу кода, связанного с транзакциями, типа бегин, блоки трай и катч, в трай комитит
                    // транзакцию, в катч ролбэк делает
    public Part createPart(String partId, String title, int supplierId){
        return createPart(partId, title, em.find(Supplier.class, supplierId));
    }

    @Transactional
    public Part createPart(String partId, String title, Supplier supplier){
        Part part = new Part (partId, title);
        part.setSupplier(supplier);

        em.persist(part);

        return part;
    }

    public Supplier createSupplier (String name){
        Supplier supplier = new Supplier();
        supplier.setName(name);

        em.persist(supplier);

        return supplier;
    }

    @SuppressWarnings("unchecked") // игнорируем вариниг
    public List<Part> findById (String partId) {
        return em.createNamedQuery(SEARCH_BY_PART_ID)
        .setParameter("partId", partId)
        .getResultList();
    }

    public EntityManager getEntityManager(){
        return em;
    }

    public List<Part> findParts(){
        return em.createQuery("from Part").getResultList();
    }

    public boolean hasSupp(){
        return em.createQuery("select count(Supplier) from Supplier").getFirstResult() > 0 ? true : false;

    }
}
