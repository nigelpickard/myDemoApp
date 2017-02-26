package com.npickard;

import com.npickard.model.FlattenedPerson;
import com.npickard.model.Person;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

/**
 * Created by npickard on 2/25/2017.
 */
@Component
public class Receiver {
    private static final Log log = LogFactory.getLog(Receiver.class);
    private static final MessagePersistenceMode messagePersistenceMode = MessagePersistenceMode.PERSIST;

//    @Autowired
//    FlattenedPersonBuilder flattenedPersonBuilder;
//
//    @Autowired
//    DefaultMessageListenerContainer amqListenerFactory;
//
//
////    Caused by: org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named 'amqConnectionFactory' is expected to be of type 'org.springframework.jms.config.JmsListenerContainerFactory' but was actually of type 'org.apache.activemq.ActiveMQConnectionFactory'
////    at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:378) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
////    at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-4.3.6.RELEASE.jar:4.3.6.RELEASE]
//
//
//
//    @JmsListener(destination = "mailbox", containerFactory = "amqListenerFactory")
//    public void receiveMessage(Person person) {
//        log.info("Received <" + person.toString() + ">");
//        flattenedPersonBuilder.createPerson(messagePersistenceMode, person);
//    }

}