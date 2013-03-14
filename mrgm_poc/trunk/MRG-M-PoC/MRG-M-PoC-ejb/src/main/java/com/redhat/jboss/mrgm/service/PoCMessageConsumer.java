package com.redhat.jboss.mrgm.service;

import com.redhat.jboss.mrgm.model.PoCMessage;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.logging.Logger;

/**
 * Message-Driven Bean implementation class for: PoCMessageConsumer
 * 
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/mrg-m/queue/pocmessages") }, mappedName = "pocmessages")
public class PoCMessageConsumer implements MessageListener {
	
	private static Logger log = Logger.getLogger(PoCMessageConsumer.class);

	@PersistenceContext
	private EntityManager em;
	
	private Session session;
	
	Context ctx;

	/**
	 * Default constructor.
	 */
	public PoCMessageConsumer() {
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
			String scenario = textMessage.getStringProperty("Scenario");
			
			log.info("Received message for scenario "+scenario);

			if ("scenario1".equals(scenario)) {
				scenario1(textMessage);
			} else if ("scenario2".equals(scenario)) {
				scenario2(textMessage);
			} else if ("scenario3".equals(scenario)) {
				scenario3(textMessage);
			} else if ("scenario4".equals(scenario)) {
				scenario4(textMessage);
			}
		} catch (JMSException e) {
			log.error(e.getMessage());
		} catch (NamingException e){
			log.error(e.getMessage());
		}
	}

	private void scenario1(TextMessage textMessage) throws JMSException {
		log.info("Received message: " + textMessage.getText());
	}

	private void scenario2(TextMessage textMessage) throws JMSException, NamingException {
		scenario1(textMessage);
		
		saveMessageToDB(textMessage);
		
		sendToReplyQueue(textMessage);
	}
	
	private void scenario3(TextMessage textMessage) throws JMSException {
		scenario1(textMessage);
	}

	private void scenario4(TextMessage textMessage) throws JMSException {
		scenario1(textMessage);
	}

    private void saveMessageToDB(TextMessage textMessage) throws JMSException {
        PoCMessage pocMessage = new PoCMessage();
        pocMessage.setText(textMessage.getText());
        pocMessage.setScenario(textMessage.getStringProperty("Scenario"));
        em.persist(pocMessage);
        
        log.info("Saved message to database");
    }

    private void sendToReplyQueue(TextMessage textMessage) throws NamingException, JMSException {
        Destination replyDestination = textMessage.getJMSReplyTo();
        Connection connection = null;
        ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("java:/QpidJMSXA");
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer replyProducer = session.createProducer(replyDestination);
        TextMessage replyMessage = session.createTextMessage();
        replyMessage.setText("This is the reply-to message for \""+textMessage.getText()+"\"");
        replyProducer.send(replyMessage);
        
        log.info("Sent back reply");
    }
}