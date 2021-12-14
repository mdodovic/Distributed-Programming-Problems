package kdp.atomicBroadcast.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kdp.atomicBroadcast.concurrentPart.AtomicBroadcastBuffer;

public class ClientRmiAtomicBroadcastBuffer<T> implements AtomicBroadcastBuffer<T> {

	private RemoteRmiAtomicBroadcastBuffer<T> stub;
	
	@SuppressWarnings("unchecked")
	public ClientRmiAtomicBroadcastBuffer(String host, int port) {
		try {
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
		
			Registry reg = LocateRegistry.getRegistry(host, port);

			stub = (RemoteRmiAtomicBroadcastBuffer<T>) reg.lookup("/buffer");
 			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public T get(int id) {
		try {
			return stub.get(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void put(T item) {
		try {
			stub.put(item);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}


	
	
}
