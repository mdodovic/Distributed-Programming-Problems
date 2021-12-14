package kdp.atomicBroadcast.rmi;

import java.rmi.RemoteException;

import kdp.atomicBroadcast.concurrentPart.ConcurrentAtomicBroadcastBuffer;

public class RemoteRmiAtomicBroadcastBufferImpl<T> implements RemoteRmiAtomicBroadcastBuffer<T> {

	private ConcurrentAtomicBroadcastBuffer<T> buffer;

	public RemoteRmiAtomicBroadcastBufferImpl(int b, int n) {
		buffer = new ConcurrentAtomicBroadcastBuffer<T>(b, n);
	}
	
	@Override
	public void put(T item) throws RemoteException {
		buffer.put(item);
	}

	@Override
	public T get(int id) throws RemoteException {
		T item = null;
		
		item = buffer.get(id);
		
		return item;
	}
	
	
	
}
