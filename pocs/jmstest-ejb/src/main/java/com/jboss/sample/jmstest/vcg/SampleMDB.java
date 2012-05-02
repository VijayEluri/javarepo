package com.jboss.sample.jmstest.vcg;

import java.util.Enumeration;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

@MessageDriven(messageListenerInterface = MessageListener.class, activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/sample"),
		@ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable") })
public class SampleMDB implements MessageListener {
	private static final Logger log = Logger.getLogger(SampleMDB.class);

	@Resource
	private MessageDrivenContext ctx;

	@Override
	public void onMessage(Message msg) {
		try {
			if (msg instanceof TextMessage) {
				log.info("## Msg received: " + ((TextMessage) msg).getText());
			} else if (msg instanceof ObjectMessage) {
				log.info("## Msg received: " + toString((ObjectMessage) msg));
			} else {
				log.info("## Invalid message type.");
			}
		} catch (JMSException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
		}
	}

	private String toString(ObjectMessage objMessage) throws JMSException {
		StringBuilder str = new StringBuilder();
		for (@SuppressWarnings("unchecked")
		Enumeration<String> e = objMessage.getPropertyNames(); e
				.hasMoreElements();) {
			String name = e.nextElement();
			str.append("[" + name + "=" + objMessage.getObjectProperty(name)
					+ "] ");
		}

		return str.toString();
	}
}
