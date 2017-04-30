package com.npickard.dao;

import com.npickard.model.FlattenedPerson;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by npickard on 4/30/2017.
 */
@Component
public class FlattenedPersonDAOImpl implements FlattenedPersonDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(FlattenedPerson flattenedPerson) {
        em.persist(flattenedPerson);
    }

    @Override
    public List<FlattenedPerson> listAll() {
        return em.createQuery("SELECT p FROM FlattenedPerson p").getResultList();
    }
}
