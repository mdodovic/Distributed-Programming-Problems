package kdp.diningSavages.cookingWithFile.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kdp.diningSavages.multiplePots.bothSide.RmiPot;

public class RmiPotInvocation implements RmiPot {
	
	private RmiPot pot;
	
	public RmiPotInvocation(String host, int port) {
		try {
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			Registry reg = LocateRegistry.getRegistry(host, port);
			
			pot = (RmiPot) reg.lookup("/pot");
				
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int cook() throws RemoteException {
		return pot.cook();
	}

}
