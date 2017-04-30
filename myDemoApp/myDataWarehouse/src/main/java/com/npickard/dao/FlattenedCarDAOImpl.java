package com.npickard.dao;

import com.npickard.model.FlattenedCar;
import com.npickard.model.FlattenedPerson;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by npickard on 4/30/2017.
 */
@Component
public class FlattenedCarDAOImpl implements FlattenedCarDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(FlattenedCar flattenedCar) {
        em.persist(flattenedCar);
    }

    @Override
    public List<FlattenedCar> listAll() {
        return em.createQuery("SELECT c FROM FlattenedCar c").getResultList();
    }
}
