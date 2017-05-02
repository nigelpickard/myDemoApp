package com.npickard.service;

import com.npickard.dao.FlattenedCarDAO;
import com.npickard.dao.FlattenedHouseDAO;
import com.npickard.model.FlattenedCar;
import com.npickard.model.FlattenedHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by npickard on 4/30/2017.
 */
@Component
public class FlattenedHouseService {

    @Autowired
    private FlattenedHouseDAO flattenedHouseDao;

    public FlattenedHouseService(){}

    @Transactional
    public void add(FlattenedHouse flattenedHouse) {
        flattenedHouseDao.save(flattenedHouse);
    }

    @Transactional
    public void addAll(Collection<FlattenedHouse> flattenedHouses) {
        for (FlattenedHouse flattenedHouse : flattenedHouses) {
            flattenedHouseDao.save(flattenedHouse);
        }
    }

    @Transactional(readOnly = true)
    public List<FlattenedHouse> listAll() {
        return flattenedHouseDao.listAll();
    }

}
