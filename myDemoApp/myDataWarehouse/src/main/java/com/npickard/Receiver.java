package com.npickard;

import com.npickard.model.FlattenedPerson;
import com.npickard.model.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by npickard on 2/25/2017.
 */
@Component
public class Receiver {
    private static final Log log = LogFactory.getLog(Receiver.class);
    private static final MessagePersistenceMode messagePersistenceMode = MessagePersistenceMode.PERSIST;

    @Autowired
    FlattenedPersonBuilder flattenedPersonBuilder;

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Person person) {
        log.info("Received <" + person.toString() + ">");
        flattenedPersonBuilder.createPerson(messagePersistenceMode, person);
    }

}