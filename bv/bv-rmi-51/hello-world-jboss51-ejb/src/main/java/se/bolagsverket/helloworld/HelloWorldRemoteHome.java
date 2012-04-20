package se.bolagsverket.helloworld;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface HelloWorldRemoteHome extends EJBHome {

	HelloWorldRemote create() throws CreateException, RemoteException;
	
}
