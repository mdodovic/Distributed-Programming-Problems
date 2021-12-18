package childCare.rmiAndSockets;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import childCare.rmiAndSockets.distributed.DistributedRmiKindergarden;

public class ParentTest {
	

	public static final int NUM_PARENTS = 4;

	public static void main(String[] args) {
		
		String host = "localhost";
		int port = 5555;

		try {
			
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			Registry reg = LocateRegistry.getRegistry(host, port);
			
			DistributedRmiKindergarden kg = (DistributedRmiKindergarden) reg.lookup("/kindergarden");
					
			for(int i = 0; i < NUM_PARENTS; i++)
				new Parent(kg, i).start();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}
}
