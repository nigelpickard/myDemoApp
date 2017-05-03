package com.npickard.dao;

import com.npickard.model.FlattenedHouse;

import java.util.List;

/**
 * Created by npickard on 4/30/2017.
 */
public interface FlattenedHouseDAO {

    public void save(FlattenedHouse flattenedHouse);

    public List<FlattenedHouse> listAll();

}
