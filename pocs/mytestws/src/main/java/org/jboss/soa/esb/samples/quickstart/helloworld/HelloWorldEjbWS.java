package org.jboss.soa.esb.samples.quickstart.helloworld;

import javax.ejb.Remote;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


import org.jboss.ws.annotation.EndpointConfig;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
@Remote
public interface HelloWorldEjbWS {

	public void sayHello(String name);
}
