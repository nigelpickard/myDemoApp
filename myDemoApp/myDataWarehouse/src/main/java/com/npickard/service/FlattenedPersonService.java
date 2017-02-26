package com.npickard.service;

import com.npickard.dao.FlattenedPersonDAO;
import com.npickard.model.FlattenedPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by npickard on 2/22/2017.
 */
@Component
public class FlattenedPersonService {

    @Autowired
    private FlattenedPersonDAO flattenedPersonDao;

    public FlattenedPersonService(){}

    @Transactional
    public void add(FlattenedPerson flattenedPerson) {
        flattenedPersonDao.save(flattenedPerson);
    }

    @Transactional
    public void addAll(Collection<FlattenedPerson> flattenedPersons) {
        for (FlattenedPerson flattenedPerson : flattenedPersons) {
            flattenedPersonDao.save(flattenedPerson);
        }
    }

    @Transactional(readOnly = true)
    public List<FlattenedPerson> listAll() {
        return flattenedPersonDao.listAll();
    }

}
