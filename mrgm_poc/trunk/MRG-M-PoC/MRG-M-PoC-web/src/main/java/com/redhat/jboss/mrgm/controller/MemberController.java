package com.redhat.jboss.mrgm.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.logging.Logger;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberController {

    @Inject
    private Logger log;
    @Inject
    private FacesContext facesContext;
    private String message = null;
    private String scenario = null;
    private Context ctx;
    private ConnectionFactory cf;

    @Produces
    @Named
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Produces
    @Named
    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    @PostConstruct
    public void init() throws NamingException {
        message = new String();
        scenario = new String();

        ctx = new InitialContext();
        cf = (ConnectionFactory) ctx.lookup("java:/QpidJMSXA");
    }

    public void send() throws Exception {
        boolean success = false;

        log.info("Testing scenario: " + scenario);
        log.info("Message in JSF: " + message);

        try {
            if ("scenario1".equals(scenario)) {
                scenario1();
            } else if ("scenario2".equals(scenario)) {
                scenario2();
            }

            success = true;
        } catch (NamingException e) {
            log.error(e.getMessage());
        } catch (JMSException e) {
            log.error(e.getMessage());
        }

        if (success) {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Send!",
                    "Message creation successful"));
        } else {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Not Send!",
                    "Message creation unsuccessful"));
        }
    }

    private void scenario1() throws JMSException, NamingException {
        Connection con = null;
        Session session = null;
        MessageProducer producer = null;

        try {
            con = cf.createConnection();
            session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            textMessage.setStringProperty("Scenario", scenario);
            Destination dest = (Queue) ctx
                    .lookup("java:jboss/exported/mrg-m/queue/pocmessages");
            producer = session.createProducer(dest);
            con.start();
            producer.send(textMessage, javax.jms.DeliveryMode.PERSISTENT,
                    javax.jms.Message.DEFAULT_PRIORITY, 1800000);
        } catch (JMSException e) {
            log.error(e.getMessage());
        } catch (NamingException e) {
            log.error(e.getMessage());
        } finally {
            if (producer != null) {
                producer.close();
            }

            if (session != null) {
                session.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    private void scenario2() throws JMSException, NamingException {
        Connection con = null;
        Session session = null;
        MessageProducer producer = null;

        try {
            con = cf.createConnection();
            session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            textMessage.setStringProperty("Scenario", scenario);
            Destination dest = (Queue) ctx
                    .lookup("java:jboss/exported/mrg-m/queue/pocmessages");
            producer = session.createProducer(dest);

            con.start();
            Destination replyQueue = (Queue) ctx
                    .lookup("java:jboss/exported/mrg-m/queue/pocreplymessages");
            textMessage.setJMSReplyTo(replyQueue);
            /*
             * MessageConsumer replyConsumer =
             * session.createConsumer(replyQueue);
             * replyConsumer.setMessageListener(new MessageListener() {
             * 
             * @Override public void onMessage(Message replyMessage) { try {
             * TextMessage replyTextMessage = (TextMessage) replyMessage;
             * log.info("Received reply " + replyTextMessage.getText()); } catch
             * (JMSException e) { log.severe(e.getMessage()); } }
             * 
             * });
             */
            producer.send(textMessage, javax.jms.DeliveryMode.PERSISTENT,
                    javax.jms.Message.DEFAULT_PRIORITY, 1800000);

            /*
             * synchronized (this) { try { wait(5000); } catch
             * (InterruptedException e) { throw new
             * JMSException(e.getMessage()); } }
             */

        } catch (JMSException e) {
            log.error(e.getMessage());
            log.error("Stacktrace: ", e);
        } catch (NamingException e) {
            log.error(e.getMessage());
            log.error("Stacktrace: ", e);
        } finally {
            if (producer != null) {
                producer.close();
            }

            if (session != null) {
                session.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }
}