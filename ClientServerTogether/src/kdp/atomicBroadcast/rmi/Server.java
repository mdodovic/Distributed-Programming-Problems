package kdp.atomicBroadcast.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import kdp.atomicBroadcast.message.Message;

public class Server {
	
	@SuppressWarnings("unchecked")
	private static void run(int port){
		try {
			
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			RemoteRmiAtomicBroadcastBuffer<Message<String>> buffer = 
					new RemoteRmiAtomicBroadcastBufferImpl<>(5, 3);
			
			RemoteRmiAtomicBroadcastBuffer<Message<String>> stub =
					(RemoteRmiAtomicBroadcastBuffer<Message<String>>) UnicastRemoteObject.exportObject(buffer, 0);
			
			Registry reg = LocateRegistry.createRegistry(port);
			reg.bind("/buffer", stub);
			
			System.out.println("Server is running!");
			
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		int port = 5555;
		run(port);
	}
	
}
