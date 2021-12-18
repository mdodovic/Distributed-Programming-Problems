package childCare.rmiAndSockets;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import childCare.rmiAndSockets.concurrent.Kindergarden;
import childCare.rmiAndSockets.distributed.DistributedRmiKindergarden;
import childCare.rmiAndSockets.distributed.DistributedRmiKindergardenImpl;

public class RmiThread extends Thread {

	private int port;
	private Kindergarden kg;

	public RmiThread(int port, Kindergarden kg) {
		super();
		this.port = port;
		this.kg = kg;
	}




	@Override
	public void run() {
		try {
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			DistributedRmiKindergarden kindergarden = new DistributedRmiKindergardenImpl(kg);
			DistributedRmiKindergarden stub = 
					(DistributedRmiKindergarden) UnicastRemoteObject.exportObject(kindergarden, 0);
			
			Registry reg = LocateRegistry.createRegistry(port);
			reg.bind("/kindergarden", stub);
			
			System.out.println("Server ready to accept remote method invocations at port: " + port);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
					
	}
	
}
