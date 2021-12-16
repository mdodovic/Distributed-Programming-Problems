package kdp.diningSavages.onePot.server;

import java.util.concurrent.Semaphore;

import kdp.diningSavages.onePot.bothSide.NetPotI;
import kdp.diningSavages.onePot.bothSide.RemotePot;

public class SemaphorePot implements NetPotI, RemotePot{

	private Semaphore mutex = new Semaphore(1);
	private Semaphore okToEat = new Semaphore(0);
	private Semaphore cookerSleep = new Semaphore(0);
	
	private int food = 0;
	public static final int MAX_FOOD = 3; 
	

	@Override
	public void cook() {

		cookerSleep.acquireUninterruptibly();
		
		food = MAX_FOOD;
		System.err.println("Food: " + food);
		okToEat.release();
	}

	@Override
	public void eat() {
		
		mutex.acquireUninterruptibly();
		if(food == 0) {
			cookerSleep.release();
			okToEat.acquireUninterruptibly();
		}		
		food --;
		System.out.println("Food: " + food);
		mutex.release();
	}

	
}
