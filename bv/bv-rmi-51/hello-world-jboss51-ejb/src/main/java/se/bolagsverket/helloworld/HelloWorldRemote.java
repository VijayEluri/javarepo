package se.bolagsverket.helloworld;
import java.rmi.RemoteException;

import javax.ejb.Remote;

@Remote
public interface HelloWorldRemote {
	
	public String sayHello() throws RemoteException;
	
	public HelloWorldEntity sayHelloEntity() throws RemoteException;
}
