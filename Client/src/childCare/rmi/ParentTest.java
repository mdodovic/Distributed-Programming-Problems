package childCare.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import childCare.rmi.distributed.RmiKindergarden;

public class ParentTest {
	

	public static final int NUM_PARENTS = 4;

	public static void main(String[] args) {
		
		try {
			
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			int port = 5555;
			String host = "localhost";
			Registry reg = LocateRegistry.getRegistry(host, port);
			
			RmiKindergarden kg = (RmiKindergarden) reg.lookup("/kindergarden");
					
			for(int i = 0; i < NUM_PARENTS; i++)
				new Parent(kg, i).start();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}
}
