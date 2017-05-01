package com.npickard;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import com.npickard.model.Person;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class JmsMessageSender {
    private static final Log log = LogFactory.getLog(JmsMessageSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

//    /**
//     * send text to default destination
//     * @param text
//     */
//    public void send(final String text) {
//
//        this.jmsTemplate.send(new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                log.info("Datawarehouse creating message " +  text);
//                Message message = session.createTextMessage(text);
//                message.setJMSReplyTo(new ActiveMQQueue("ConfirmMessageReceivedQueue"));
//                return message;
//            }
//        });
//    }

}

