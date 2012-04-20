package it.csi.poc.soa;

import java.util.HashMap;
import java.util.Map;

import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;


public class PrepareGetCustomerByIdSOAPClient   {

	public PrepareGetCustomerByIdSOAPClient(ConfigTree config) {}

	public Message process(Message m) {
		HashMap requestMap = new HashMap();

		//Parameter to activate dump of soap message on console
		requestMap.put("dumpSOAP","dumpSOAP");
		
		
		Map map = (Map)m.getBody().get();
		Customer cust = (Customer)map.get("customer");
		
		//cust.setCustomerId(54);
		
		requestMap.put("getCustomerById.customerId",cust.getCustomerId());
		m.getBody().add(requestMap);
		return m;

	}
}
