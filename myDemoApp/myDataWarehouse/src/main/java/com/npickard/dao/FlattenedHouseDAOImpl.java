package com.npickard.dao;

import com.npickard.model.FlattenedCar;
import com.npickard.model.FlattenedHouse;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by npickard on 4/30/2017.
 */
@Component
public class FlattenedHouseDAOImpl implements FlattenedHouseDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(FlattenedHouse flattenedHouse) {
        em.persist(flattenedHouse);
    }

    @Override
    public List<FlattenedHouse> listAll() {
        return em.createQuery("SELECT h FROM FlattenedHouse h").getResultList();
    }
}
