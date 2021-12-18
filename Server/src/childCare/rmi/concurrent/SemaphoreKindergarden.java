package childCare.rmi.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreKindergarden implements Kindergarden {

	private Semaphore mutex = new Semaphore(1);
	
	private int nChild = 0;
	private int nNannies = 0;
	private int nWaitingNannies = 0;

	private Semaphore nannyExit = new Semaphore(0);
	private Semaphore nannyLeft = new Semaphore(0);
	
	
	@Override
	public boolean bringChildren(int numOfChildren) {
		mutex.acquireUninterruptibly();
		if( (nChild + numOfChildren) <= nNannies * Kindergarden.NUMBER_OF_CHILDREN_PER_NANNY) {
			
			nChild += numOfChildren;
			
			System.out.println(Thread.currentThread().getName() + " leave " + numOfChildren);
			mutex.release();
			return true;
		} else {
			System.out.println(Thread.currentThread().getName() + " failed in leaving");
			mutex.release();
			return false;
		}
	}

	@Override
	public void takeChildren(int numOfChildren) {
		mutex.acquireUninterruptibly();
		
		nChild -= numOfChildren;
		System.out.println(Thread.currentThread().getName() + " took " + numOfChildren);
		
		int nannyToFree = nNannies - nChild / Kindergarden.NUMBER_OF_CHILDREN_PER_NANNY;
		
		if(nWaitingNannies < nannyToFree) {
			nannyToFree = nWaitingNannies;
		}
		
		for(int i = 0; i < nannyToFree; i++) {
			nannyExit.release();
			nannyLeft.acquireUninterruptibly();
		}
		mutex.release();
	}

	@Override
	public void nannyEnter() {
		mutex.acquireUninterruptibly();
		
		nNannies++;
		System.out.println(Thread.currentThread().getName() + " entered");
		if(nWaitingNannies > 0) {
			nannyExit.release();
			nannyLeft.acquireUninterruptibly();
		}
		mutex.release();
	}

	@Override
	public void nannyExit() {
		mutex.acquireUninterruptibly();
		
		if(nChild / Kindergarden.NUMBER_OF_CHILDREN_PER_NANNY <= (nNannies - 1)) {
			nNannies --;
			System.out.println(Thread.currentThread().getName() + " left");
			mutex.release();
		} else {
			nWaitingNannies++;
			
			mutex.release();
			
			nannyExit.acquireUninterruptibly();
			nWaitingNannies--;
			System.out.println(Thread.currentThread().getName() + " left");
			nNannies--;
			nannyLeft.release();
		}
	}

}
