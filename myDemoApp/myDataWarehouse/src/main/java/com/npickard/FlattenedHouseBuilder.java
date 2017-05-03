package com.npickard;

import com.npickard.model.FlattenedHouse;
import com.npickard.model.House;
import com.npickard.service.FlattenedHouseService;
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
public class FlattenedHouseBuilder implements ApplicationContextAware {
    private static final Log log = LogFactory.getLog(FlattenedHouseBuilder.class);
    private ApplicationContext applicationContext;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private FlattenedHouseService flattenedHouseService;

    @Autowired
    private JmsTemplate jmsTemplate;

    public FlattenedHouseBuilder(){}

    public void createHouse(MessagePersistenceMode messagePersistenceMode, House house){

        if (messagePersistenceMode == null){
            log.warn("Datawarehouse: Message Persistence Mode is null; no operation done.");
        }

        if (house == null){
            log.warn("Datawarehouse: FlattenedHouse to create is null!");
        }

        if (MessagePersistenceMode.MESSAGE.equals(messagePersistenceMode)){
            log.info("Datawarehouse: Trying to send a message for DataWarehouse; not a valid operation.");
        }

        if (MessagePersistenceMode.PERSIST.equals(messagePersistenceMode)){
            FlattenedHouse flattenedHouse = new FlattenedHouse(house);
            log.info("Datawarehouse: Persisting flattened house: " + flattenedHouse.toString());
            flattenedHouseService.add(flattenedHouse);
        }
    }

    public List<FlattenedHouse> getAllHouses(){
        return flattenedHouseService.listAll();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
