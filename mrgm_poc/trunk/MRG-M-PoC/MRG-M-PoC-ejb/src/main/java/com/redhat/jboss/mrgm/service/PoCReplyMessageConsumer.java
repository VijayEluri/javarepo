package com.redhat.jboss.mrgm.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.logging.Logger;

/**
 * Message-Driven Bean implementation class for: PoCMessageConsumer
 *
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/mrg-m/queue/pocreplymessages")}, mappedName = "pocreplymessages")
public class PoCReplyMessageConsumer implements MessageListener {

    private static Logger log = Logger.getLogger(PoCReplyMessageConsumer.class);
    private Session session;
    Context ctx;

    /**
     * Default constructor.
     */
    public PoCReplyMessageConsumer() {
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            log.info("Received reply-message: \"" + textMessage.getText() + "\"");
        } catch (JMSException e) {
            log.error(e.getMessage());
        }
    }
}