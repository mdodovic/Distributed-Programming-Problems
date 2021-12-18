package diningSavages.sockets.concurrent;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	
	private String path;
	
	private static int cnt = 1;
	
	public SemaphorePot(int numFoodTypes, int maxFoodInPots, String path) {
		this.maxFoodInPots = maxFoodInPots;
		
		mutex = new Semaphore[numFoodTypes];
		okToEat = new Semaphore[numFoodTypes];
		food = new int[numFoodTypes];
		
		for(int i = 0; i < numFoodTypes; i++) {
			mutex[i] = new Semaphore(1);
			okToEat[i] = new Semaphore(0);
			food[i] = 0;			
		}
				
		this.path = path;
	}

	@Override
	public int cook() {
		
		sleep.acquireUninterruptibly();
		
		int type = queue.remove();
		food[type] = maxFoodInPots;
		
		System.out.println(path);
		try(FileWriter f = new FileWriter(path);
			PrintWriter pw = new PrintWriter(f)){
			
			for(int i = 0; i < maxFoodInPots; i++)
				pw.write("level: " + cnt + " type: " + type + " data: " + i + " food (id: " + (int)(Math.random() * 100 + 1) + ")\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		cnt++;
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
