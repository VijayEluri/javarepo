package se.netent.server.mdb;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

import org.jboss.ejb3.annotation.ResourceAdapter;

@MessageDriven(name = "MDB_TestQueue",
activationConfig =
{
   @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
   @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/testQueue"),
   @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
@ResourceAdapter("jms-ra.rar")
public  class TestMDB implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		System.out.println("Recieved message");

	}

}
