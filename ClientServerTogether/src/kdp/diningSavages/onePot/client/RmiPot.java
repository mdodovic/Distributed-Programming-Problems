package kdp.diningSavages.onePot.client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kdp.diningSavages.onePot.bothSide.RemotePot;

public class RmiPot implements RemotePot {

	private RemotePot pot;
	
	public RmiPot(String host, int port) {
		try {
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		Registry reg = LocateRegistry.getRegistry(host, port);
		
		pot = (RemotePot) reg.lookup("/pot");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void cook() {
		try {
			pot.cook();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
