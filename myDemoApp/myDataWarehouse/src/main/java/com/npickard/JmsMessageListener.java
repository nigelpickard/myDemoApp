package com.npickard;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.npickard.Serializer.HouseJAXBDeserializer;
import com.npickard.model.Car;
import com.npickard.model.House;
import com.npickard.model.Person;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
/**
 * POJO class, have handleMessage(...) method.
 * The return of handleMessage(...) will be
 * automatically send back to message.getJMSReplyTo().
 */
public class JmsMessageListener {
    private static final Log log = LogFactory.getLog(JmsMessageListener.class);
    private MessagePersistenceMode messagePersistenceMode = MessagePersistenceMode.PERSIST;

    @Autowired
    FlattenedPersonBuilder flattenedPersonBuilder;

    @Autowired
    FlattenedCarBuilder flattenedCarBuilder;

    @Autowired
    FlattenedHouseBuilder flattenedHouseBuilder;

    @Autowired
    HouseJAXBDeserializer jaxbDeserializer;

    /**
     * its text, so its serialized into some form.   Could
     * be just text, json or xml
     * @param text
     * @return
     */
    public String handleMessage(String text) {
        log.info("Datawarehouse: Received: " + text);

        //deserialize here to see what we have
        try {
            House house = new ObjectMapper().readValue(text, House.class);
            log.info("Datawarehouse: deserialized house using JSON deserializer");
            flattenedHouseBuilder.createHouse(messagePersistenceMode, house);
            return "ACK from Datawarehouse handleMessage for person " + text;
        } catch (Exception e1) {
            try {
                House house = jaxbDeserializer.deserialize(text);
                log.info("Datawarehouse: deserialized house using JAXB deserializer");
                flattenedHouseBuilder.createHouse(messagePersistenceMode, house);
                return "ACK from Datawarehouse handleMessage for person " + text;
            }catch(Exception e2) {
                //we may have  a person
                log.info("Datawarehouse: House deserialization failed, trying person....");
                flattenedPersonBuilder.createPerson(messagePersistenceMode, new Person(text));
                return "ACK from Datawarehouse handleMessage for person " + text;
            }
        }
    }

    public String handleMessage(Car car) {
        log.info("Datawarehouse: Received: " + car);
        flattenedCarBuilder.createCar(messagePersistenceMode, car);
        return "ACK from Datawarehouse handleMessage for car " + car.toString();
    }

}