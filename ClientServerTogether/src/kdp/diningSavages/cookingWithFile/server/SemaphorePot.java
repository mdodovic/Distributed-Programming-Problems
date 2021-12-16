package kdp.diningSavages.cookingWithFile.server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

import kdp.diningSavages.multiplePots.bothSide.NetPot;
import kdp.diningSavages.multiplePots.bothSide.RmiPot;

public class SemaphorePot implements NetPot, RmiPot{

	private int numFoodTypes;
	private int maxFoodPerPot;
	
	private int[] food;
	private Semaphore[] mutex;
	private Semaphore[] okToEat;
	private Semaphore sleepCook;
	
	private List<Integer> queue = new LinkedList<Integer>();
	private Semaphore queueMutex = new Semaphore(1);
	
	private static int cnt = 0;
	
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
		
		
		try (FileWriter f = new FileWriter(Server.PATH, true); 
			BufferedWriter b = new BufferedWriter(f); 
			PrintWriter p = new PrintWriter(b)) { 

			for(int i = 0; i < maxFoodPerPot; i++) {
				p.write(String.format("Iteracija: %d - %d\n", cnt, (int) (Math.random() * 100 + 1)));
			}
			cnt++;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
