package it.csi.poc.soa;

import org.jboss.soa.esb.actions.ActionProcessingException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;


public class ResponsePipeline   {

	public ResponsePipeline(ConfigTree config) {}

	public Message process(Message m) throws ActionProcessingException {
		
		//config.
		
		//throw new ActionProcessingException("EX");
		//return m;
		
		
		 String responseMsg = (String) m.getBody().get("MsgToClient");
		 m.getBody().add(responseMsg);
		 
		 return m;

	}
}
