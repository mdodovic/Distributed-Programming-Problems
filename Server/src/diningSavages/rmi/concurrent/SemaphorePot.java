package diningSavages.rmi.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;


public class SemaphorePot implements DiningSavages{

	private Semaphore[] mutex;
	private Semaphore[] okToEat;
	private int[] food;
	
	private Semaphore sleep = new Semaphore(0);
	private BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
	
	private int maxFoodInPots;
	
	public SemaphorePot(int numFoodTypes, int maxFoodInPots) {
		this.maxFoodInPots = maxFoodInPots;
		
		mutex = new Semaphore[numFoodTypes];
		okToEat = new Semaphore[numFoodTypes];
		food = new int[numFoodTypes];
		
		for(int i = 0; i < numFoodTypes; i++) {
			mutex[i] = new Semaphore(1);
			okToEat[i] = new Semaphore(0);
			food[i] = 0;			
		}
				
	}

	@Override
	public int cook() {
		
		sleep.acquireUninterruptibly();
		
		int type = queue.remove();
		food[type] = maxFoodInPots;
		okToEat[type].release();
		
		return type;	
	}

	@Override
	public void eat(int type) {
		mutex[type].acquireUninterruptibly();
		
		if(food[type] == 0) {
			queue.add(type);
			sleep.release();
			okToEat[type].acquireUninterruptibly();
		}
		food[type] -- ;
		
		mutex[type].release();
	}
	
}
