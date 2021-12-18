package childCare.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import childCare.rmi.concurrent.Kindergarden;
import childCare.rmi.concurrent.SemaphoreKindergarden;
import childCare.rmi.distributed.RmiKindergarden;
import childCare.rmi.distributed.RmiKindergardenImpl;

public class Server {

	public static void main(String[] args) {
		try {
		
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			int port = 5555;
			Kindergarden kg = new SemaphoreKindergarden();
			
			RmiKindergarden kinder = new RmiKindergardenImpl(kg);
		
			RmiKindergarden stub = (RmiKindergarden) UnicastRemoteObject.exportObject(kinder, 0);
			
			Registry reg = LocateRegistry.createRegistry(port);
			reg.bind("/kindergarden", stub);
			
			System.out.println("Server is waiting for remote methods invocations at port " + port);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
