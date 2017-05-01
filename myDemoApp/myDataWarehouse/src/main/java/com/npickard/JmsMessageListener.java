package com.npickard;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.npickard.model.Car;
import com.npickard.model.Person;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;


@Service
/**
 * POJO class, have handleMessage(...) method.
 * The return of handleMessage(...) will be
 * automatically send back to message.getJMSReplyTo().
 */
public class JmsMessageListener {
    private static final Log log = LogFactory.getLog(JmsMessageListener.class);
    MessagePersistenceMode messagePersistenceMode = MessagePersistenceMode.PERSIST;

    @Autowired
    FlattenedPersonBuilder flattenedPersonBuilder;

    @Autowired
    FlattenedCarBuilder flattenedCarBuilder;

    public String handleMessage(String text) {
        log.info("Datawarehouse: Received: " + text);
        flattenedPersonBuilder.createPerson(messagePersistenceMode, new Person(text));
        return "ACK from Datawarehouse handleMessage for person " + text;
    }

    public String handleMessage(Car car) {
        log.info("Datawarehouse: Received: " + car);
        flattenedCarBuilder.createCar(messagePersistenceMode, car);
        return "ACK from Datawarehouse handleMessage for car " + car.toString();
    }
}