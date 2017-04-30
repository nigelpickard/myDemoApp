package com.npickard.service;

import com.npickard.dao.FlattenedCarDAO;
import com.npickard.dao.FlattenedPersonDAO;
import com.npickard.model.FlattenedCar;
import com.npickard.model.FlattenedPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by npickard on 4/30/2017.
 */
@Component
public class FlattenedCarService {

    @Autowired
    private FlattenedCarDAO flattenedCarDao;

    public FlattenedCarService(){}

    @Transactional
    public void add(FlattenedCar flattenedCar) {
        flattenedCarDao.save(flattenedCar);
    }

    @Transactional
    public void addAll(Collection<FlattenedCar> flattenedCars) {
        for (FlattenedCar flattenedCar : flattenedCars) {
            flattenedCarDao.save(flattenedCar);
        }
    }

    @Transactional(readOnly = true)
    public List<FlattenedCar> listAll() {
        return flattenedCarDao.listAll();
    }

}
