package it.csi.cxf.wss5.presentation.war.action.service;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.jboss.ws.annotation.EndpointConfig;

@Stateless 
@WebService
@EndpointConfig(configName = "Standard WSSecurity Endpoint")
public class HelloWorldImpl implements HelloWorld {
	
	@WebMethod
    public String sayHi(String t) {
        System.out.println("-----> SERVER : sayHi called [" + t + "]");
        return ("Hello " + t);
    }
}