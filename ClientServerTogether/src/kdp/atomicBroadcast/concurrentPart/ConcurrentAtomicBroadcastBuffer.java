package kdp.atomicBroadcast.concurrentPart;

import java.util.concurrent.Semaphore;

public class ConcurrentAtomicBroadcastBuffer<T> implements AtomicBroadcastBuffer<T> {
	private int B;
	private int N;

	private T[] buffer;
	private int cnt[];
	private int writeIndex;
	private int readIndex[];

	private Semaphore empty;
	private Semaphore mutex[];
	private Semaphore full[];
	private Semaphore mutexP;

	@SuppressWarnings("unchecked")
	public ConcurrentAtomicBroadcastBuffer(int B, int N) {
		this.B = B;
		this.N = N;

//		this.buffer = Array.newInstance(componentType, B)
		this.buffer = (T[]) new Object[B];

		this.writeIndex = 0;
		this.empty = new Semaphore(B);
		this.cnt = new int[B];
		this.readIndex = new int[N];
		this.full = new Semaphore[N];
		this.mutexP = new Semaphore(1);
		this.mutex = new Semaphore[B];

		for (int i = 0; i < N; i++) {
			this.full[i] = new Semaphore(0);
		}

		for (int i = 0; i < B; i++) {
			this.mutex[i] = new Semaphore(1);
		}
	}

	@Override
	public T get(int id) {
		if (id < 0 || id >= N) {
			return null;
		}
		
		full[id].acquireUninterruptibly();
		T item = buffer[readIndex[id]];
		
		mutex[readIndex[id]].acquireUninterruptibly();
		
		cnt[readIndex[id]]++;
		if (cnt[readIndex[id]] == N) {
			cnt[readIndex[id]] = 0;
			empty.release();
		}
		
		mutex[readIndex[id]].release();
		readIndex[id] = (readIndex[id] + 1) % B;
		
		return item;	
	}

	@Override
	public void put(T item) {
		empty.acquireUninterruptibly();
		
		mutexP.acquireUninterruptibly();
		buffer[writeIndex] = item;
		writeIndex = (writeIndex + 1) % B;
		mutexP.release();
		
		for (int i = 0; i < full.length; i++) {
			full[i].release();
		}
	}

}
