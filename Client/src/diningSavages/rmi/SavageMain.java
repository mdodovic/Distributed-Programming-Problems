package diningSavages.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import diningSavages.rmi.distributed.Pot;

public class SavageMain {
	public static final int NUM_SAVAGES = 5;
	public static final int NUM_FOOD_TYPES = 4;

	public static void main(String[] args) {
		
		String  host = "localhost";
		int port = 5555;
	
		try {
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			Registry reg = LocateRegistry.getRegistry(host, port);
			
			Pot pot = (Pot) reg.lookup("/pot");

			for(int i = 0; i < NUM_SAVAGES; i++)
				new Savage(pot,i);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}		
	}

	
}
