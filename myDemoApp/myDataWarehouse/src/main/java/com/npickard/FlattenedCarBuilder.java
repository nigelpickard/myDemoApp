package com.npickard;

import com.npickard.model.Car;
import com.npickard.model.FlattenedCar;
import com.npickard.model.FlattenedPerson;
import com.npickard.model.Person;
import com.npickard.service.FlattenedCarService;
import com.npickard.service.FlattenedPersonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by npickard on 4/30/2017.
 */
@Component
public class FlattenedCarBuilder implements ApplicationContextAware {
    private static final Log log = LogFactory.getLog(FlattenedCarBuilder.class);
    private ApplicationContext applicationContext;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private FlattenedCarService flattenedCarService;

    @Autowired
    private JmsTemplate jmsTemplate;

    public FlattenedCarBuilder(){}

    public void createCar(MessagePersistenceMode messagePersistenceMode, Car car){

        if (messagePersistenceMode == null){
            log.warn("Message Persistence Mode is null; no operation done.");
        }

        if (car == null){
            log.warn("FlattenedCar to create is null!");
        }

        if (MessagePersistenceMode.MESSAGE.equals(messagePersistenceMode)){
            log.info("Trying to send a message for DataWarehouse; not a valid operation.");
        }

        if (MessagePersistenceMode.PERSIST.equals(messagePersistenceMode)){
            FlattenedCar flattenedCar = new FlattenedCar(car);
            log.info("Persisting flattened car: " + flattenedCar.toString());
            flattenedCarService.add(flattenedCar);
        }
    }

    public List<FlattenedCar> getAllCars(){
        return flattenedCarService.listAll();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
