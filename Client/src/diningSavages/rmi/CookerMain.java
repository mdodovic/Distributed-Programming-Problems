package diningSavages.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import diningSavages.rmi.distributed.Pot;

public class CookerMain {

	
	public static void main(String[] args) {
		
		String  host = "localhost";
		int port = 5555;
	
		try {
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			Registry reg = LocateRegistry.getRegistry(host, port);
			
			Pot pot = (Pot) reg.lookup("/pot");

			new Cook(pot);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}		
	}
}
