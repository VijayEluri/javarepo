package se.bolagsverket.helloworld.web;

import java.io.IOException;
import java.rmi.RMISecurityManager;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.bolagsverket.infra.sakerhet.directoryservice.facade.DirectoryServiceFacadeRemote;

public class HelloWorldServlet extends HttpServlet implements Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7250765921001045465L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			resp.getWriter().println("<H1>Servlet för att testa anrop från JBoss 5.1 -> JBoss 4.3 !</H1>");
		
			v directoryServiceFacadeRemote = getDirectoryServiceFacade();
			
			directoryServiceFacadeRemote.verifyUser("bjs");
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	private DirectoryServiceFacadeRemote getDirectoryServiceFacade() throws Exception {
		try {
			String url = "corbaloc::172.20.91.147:3528/JBoss/Naming/root";
			
			Hashtable<String,String> env = new Hashtable<String, String>();
			if ( System.getSecurityManager() == null )
			{
			 System.setSecurityManager(new RMISecurityManager());
			}
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
			env.put(Context.PROVIDER_URL, url);
			InitialContext ctx = new InitialContext(env);
			Object o = ctx.lookup("inf-dirs-rgl-ear/DirectoryServiceFacadeBean/remote");
			DirectoryServiceFacadeRemote remote = (DirectoryServiceFacadeRemote)PortableRemoteObject.narrow(o, DirectoryServiceFacadeRemote.class);
			return remote;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} 
	}

	
}
