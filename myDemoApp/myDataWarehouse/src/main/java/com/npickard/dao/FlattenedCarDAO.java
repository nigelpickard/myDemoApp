package com.npickard.dao;

import com.npickard.model.FlattenedCar;
import com.npickard.model.FlattenedPerson;

import java.util.List;

/**
 * Created by npickard on 4/30/2017.
 */
public interface FlattenedCarDAO {

    public void save(FlattenedCar flattenedCar);

    public List<FlattenedCar> listAll();

}
