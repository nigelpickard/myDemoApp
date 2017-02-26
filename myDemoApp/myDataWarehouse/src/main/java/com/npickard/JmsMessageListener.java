package com.npickard;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

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

    public String handleMessage(String text) {
        log.info("Received: " + text);
        flattenedPersonBuilder.createPerson(messagePersistenceMode, new Person(text));
        return "ACK from handleMessage";
    }
}

//@Service
///**
// * Listener Implement Spring SessionAwareMessageListener Interface
// */
//public class JmsMessageListener implements SessionAwareMessageListener<TextMessage> {
//    private static final Log log = LogFactory.getLog(JmsMessageListener.class);
//
//
//    @Override
//    public void onMessage(TextMessage message, Session session) throws JMSException {
//        // This is the received message
//        log.info("Receive: " + message.getText());
//
//        // Let's prepare a reply message - a "ACK" String
//        ActiveMQTextMessage textMessage = new ActiveMQTextMessage();
//        log.info("Got JMS message: " + message);
//        textMessage.setText("ACK");
//
//        // Message send back to the replyTo address of the income message.
//        // Like replying an email somehow.
//        MessageProducer producer = session.createProducer(message.getJMSReplyTo());
//        producer.send(textMessage);
//    }
//
//}