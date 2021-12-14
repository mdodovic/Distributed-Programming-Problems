package kdp.atomicBroadcast.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteRmiAtomicBroadcastBuffer<T> extends Remote{

	public abstract void put(T item) throws RemoteException;
	public abstract T get(int id) throws RemoteException;
}
