package com.npickard;

import com.npickard.model.FlattenedPerson;
import com.npickard.model.Person;
import com.npickard.service.FlattenedPersonService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by npickard on 4/30/2017.
 */
@Component
public class FlattenedPersonBuilder implements ApplicationContextAware {
    private static final Log log = LogFactory.getLog(FlattenedPersonBuilder.class);
    private ApplicationContext applicationContext;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private FlattenedPersonService flattenedPersonService;

    @Autowired
    private JmsTemplate jmsTemplate;

    public FlattenedPersonBuilder(){}

    public void createPerson(MessagePersistenceMode messagePersistenceMode, Person person){

        if (messagePersistenceMode == null){
            log.warn("Datawarehouse: Message Persistence Mode is null; no operation done.");
        }

        if (person == null){
            log.warn("Datawarehouse: FlattenedPerson to create is null!");
        }

        if (MessagePersistenceMode.MESSAGE.equals(messagePersistenceMode)){
            log.info("Datawarehouse: Trying to send a message for DataWarehouse; not a valid operation.");
        }

        if (MessagePersistenceMode.PERSIST.equals(messagePersistenceMode)){
            FlattenedPerson flattenedPerson = new FlattenedPerson(person);
            log.info("Datawarehouse: Persisting flattened person: " + flattenedPerson.toString());
            flattenedPersonService.add(flattenedPerson);
        }
    }

    public List<FlattenedPerson> getAllPersons(){
        return flattenedPersonService.listAll();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
