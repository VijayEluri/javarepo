package com.jboss.sample.jmstest;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public class JMSClient {
	private static final Logger log = Logger.getLogger(JMSClient.class);

	private final static String CF = "/ConnectionFactory";
	private final static String DESTINATION = "queue/sample";
	private final static String PROVIDER_URL = "jnp://localhost:1099";

	private static InitialContext context;
	private static ConnectionFactory connectionFactory;
	private static Connection conn;
	private static Session session;
	private static Destination destination;
	private static MessageProducer producer;

	public static void main(String[] args) throws Exception {
		run(100, 1000);
	}

	private static void run(int count, long delay) throws NamingException, Exception {
		Properties jboss = new Properties();
		jboss.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		jboss.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
		jboss.put("java.naming.provider.url", PROVIDER_URL);
		try {
			context = new InitialContext(jboss);
			connectionFactory = (ConnectionFactory) context.lookup(CF);
			destination = (Destination) context.lookup(DESTINATION);
			conn = connectionFactory.createConnection();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(destination);
			conn.start();

			for (int i = 0; i < count; i++) {
				send("Hello World " + i);
				try {
					Thread.sleep(delay);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (context != null) {
				try {
					context.close();
				} catch (Exception ex) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
	}

	private static void send(String msg) throws JMSException {
		TextMessage message = session.createTextMessage(msg);
		producer.send(message);
		log.info("Message sent: " + msg);
	}
}
