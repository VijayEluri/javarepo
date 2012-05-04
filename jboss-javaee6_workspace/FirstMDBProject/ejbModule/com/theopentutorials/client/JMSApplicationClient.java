package com.theopentutorials.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;

import com.theopentutorials.mdb.to.Employee;

public class JMSApplicationClient {

	public static void main(String[] args) {
		TransportConfiguration transportConfiguration = new TransportConfiguration(
				NettyConnectorFactory.class.getName());

		ConnectionFactory factory = (ConnectionFactory) HornetQJMSClient
				.createConnectionFactoryWithoutHA(JMSFactoryType.CF, transportConfiguration);

		// The queue name should match the jms-queue name in standalone.xml
		Queue queue = HornetQJMSClient.createQueue("testQueue");
		Connection connection;
		try {
			connection = factory.createConnection();
			Session session = connection.createSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);

			MessageProducer producer = session.createProducer(queue);

			// 1. Sending TextMessage to the Queue
			TextMessage message = session.createTextMessage();
			message.setText("Hello EJB3 MDB Queue!!!");
			producer.send(message);
			System.out.println("1. Sent TextMessage to the Queue");

			// 2. Sending ObjectMessage to the Queue
			ObjectMessage objMsg = session.createObjectMessage();

			Employee employee = new Employee();
			employee.setId(2163);
			employee.setName("Kumar");
			employee.setDesignation("CTO");
			employee.setSalary(100000);
			objMsg.setObject(employee);
			producer.send(objMsg);
			System.out.println("2. Sent ObjectMessage to the Queue");

			session.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}