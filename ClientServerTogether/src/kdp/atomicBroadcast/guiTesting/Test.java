package kdp.atomicBroadcast.guiTesting;

import kdp.atomicBroadcast.concurrentPart.AtomicBroadcastBuffer;
import kdp.atomicBroadcast.message.Message;
import kdp.atomicBroadcast.rmi.ClientRmiAtomicBroadcastBuffer;

public class Test {

	public static final int N = 3;
	public static final int B = 5;
	
	public static void main(String[] args) {
	
		int n = N; // number of consumers
		int m = 2; // number of producers

//		AtomicBroadcastBuffer<Message<String>> buffer = new ConcurrentAtomicBroadcastBuffer<>(B, N);
		
		
//		AtomicBroadcastBuffer<Message<String>> buffer = new ClientNetAtomicBroadcastBuffer<>("localhost", 5555);
		// This is client side. It only call servers (at ip:socket) interactions with buffer

		AtomicBroadcastBuffer<Message<String>> buffer = new ClientRmiAtomicBroadcastBuffer<Message<String>>("localhost", 5555);
		// This is client side. It only remote call methods
		
		Get[] consumer = new Get[n];
		Put[] producer = new Put[m];
		
		for(int i = 0; i < n; i++) {
			consumer[i] = new Get(buffer, i);
		}
		
		for(int i = 0; i < m; i++) {
			producer[i] = new Put(buffer);
		}
		
	}
	
}
