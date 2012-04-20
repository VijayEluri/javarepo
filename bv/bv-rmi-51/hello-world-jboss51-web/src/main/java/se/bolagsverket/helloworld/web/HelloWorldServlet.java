package se.bolagsverket.helloworld.web;

import java.io.IOException;
import java.rmi.RMISecurityManager;
import java.util.Hashtable;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.bolagsverket.helloworld.HelloWorldRemote;

public class HelloWorldServlet extends HttpServlet implements Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7250765921001045465L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.getWriter().println("<H1>Servlet för att testa anrop från JBoss 5.1 -> JBoss 4.3 !</H1>");
	
		HelloWorldRemote helloWorldRemote = getHelloWorldRemote();
		
		helloWorldRemote.sayHello();
		helloWorldRemote.sayHelloEntity();

		
	}
	
	private HelloWorldRemote getHelloWorldRemote() throws EJBException {
		try {
			String url = System.getProperty("helloworld.jboss43.corba.location");
			//String url = "jnp://172.20.138.13:1099";
						
			Hashtable<String,String> env = new Hashtable<String, String>();
			if ( System.getSecurityManager() == null )
			{
			 System.setSecurityManager(new RMISecurityManager());
			}
			
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");			
			//env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			env.put(Context.PROVIDER_URL, url);
			InitialContext ctx = new InitialContext(env);
			Object o = ctx.lookup("se.bolagsverket.helloworld.HelloWorldBean");
			HelloWorldRemote remote = (HelloWorldRemote)PortableRemoteObject.narrow(o, HelloWorldRemote.class);
			return remote;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		} 
	}
	
}
