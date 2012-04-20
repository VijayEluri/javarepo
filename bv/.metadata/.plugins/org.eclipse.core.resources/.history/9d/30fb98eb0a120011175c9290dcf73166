package se.bolagsverket.helloworld;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.IIOP;
import org.jboss.annotation.ejb.RemoteBinding;

/**
 * Session Bean implementation class HelloWorld
 */
@Stateless
@Remote(HelloWorldRemote.class)
//@IIOP(interfaceRepositorySupported=false)
@RemoteBinding(jndiBinding="se.bolagsverket.helloworld.HelloWorldBean")
public class HelloWorldBean implements HelloWorldRemote {
    
	@Resource SessionContext ctx;
	
	public HelloWorldBean() {
		// TODO Auto-generated constructor stub
	}
			
	public String sayHello() {	
		return "Hello from JBoss 4.3 CP03";
	}
	
	public HelloWorldEntity sayHelloEntity() {		
		return new HelloWorldEntity("Hello from JBoss 4.3 CP03");
	}	
}
