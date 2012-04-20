package org.jboss.soa.esb.samples.quickstart.helloworld;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.jboss.soa.esb.client.ServiceInvoker;
import org.jboss.soa.esb.listeners.message.MessageDeliverException;
import org.jboss.soa.esb.message.Message;
import org.jboss.soa.esb.message.format.MessageFactory;


@WebService(serviceName = "HelloWorldWS", endpointInterface = "org.jboss.soa.esb.samples.quickstart.helloworld.HelloWorldEjbWS")
@Stateless

public class HelloWorldEjbWSBean implements HelloWorldEjbWS{

	@WebMethod
	public void sayHello(String name) {
		
		try {
	
			ServiceInvoker invoker = new ServiceInvoker("QuickStart","HelloworldEjb3WS");
			Message message = MessageFactory.getInstance().getMessage();
			message.getBody().add("Hello " + name);
			invoker.deliverAsync(message);
			
	
		} catch (MessageDeliverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
