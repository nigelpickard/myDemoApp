package com.npickard.dao;

import com.npickard.model.FlattenedPerson;

import java.util.List;

/**
 * Created by npickard on 2/22/2017.
 */
public interface FlattenedPersonDAO {

    public void save(FlattenedPerson flattenedPerson);

    public List<FlattenedPerson> listAll();

}
