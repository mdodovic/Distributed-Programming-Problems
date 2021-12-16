package kdp.diningSavages.multiplePots.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphorePot implements Pot{

	private int numFoodTypes;
	private int maxFoodPerPot;
	
	private int[] food;
	private Semaphore[] mutex;
	private Semaphore[] okToEat;
	private Semaphore sleepCook;
	
	private List<Integer> queue = new LinkedList<Integer>();
	private Semaphore queueMutex = new Semaphore(1);
	
	public SemaphorePot(int numFoodTypes, int maxFoodPerPot) {
		this.numFoodTypes = numFoodTypes;
		this.maxFoodPerPot = maxFoodPerPot;
		food = new int[numFoodTypes];
		mutex = new Semaphore[numFoodTypes];
		okToEat = new Semaphore[numFoodTypes];
		for(int i = 0; i < numFoodTypes; i++) {
			food[i] = 0;
			mutex[i] = new Semaphore(1);
			okToEat[i] = new Semaphore(0);
		}
		sleepCook = new Semaphore(0);
	}

	@Override
	public int cook() {
		sleepCook.acquireUninterruptibly();
			
		queueMutex.acquireUninterruptibly();
		int type = queue.remove(0);
		queueMutex.release();
		
		food[type] = maxFoodPerPot;
		
		okToEat[type].release();
		
		return type;
	}

	@Override
	public void eat(int type) {
		if(type < 0 || type >= numFoodTypes)
			return;
		
		mutex[type].acquireUninterruptibly();
		
		if(food[type] == 0) {
			queueMutex.acquireUninterruptibly();
			queue.add(type);
			queueMutex.release();
			sleepCook.release();
			okToEat[type].acquireUninterruptibly();
		}
		
		food[type]--;
		
		mutex[type].release();
		
	}

}
