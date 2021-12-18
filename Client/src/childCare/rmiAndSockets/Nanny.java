package childCare.rmiAndSockets;

import childCare.rmiAndSockets.distributed.DistributedNetKindergarden;

public class Nanny extends Thread {

	DistributedNetKindergarden kg;
	
	public Nanny(DistributedNetKindergarden kg, int i) {
		super("N" + i);
		this.kg = kg;

	}
	
	@Override
	public void run() {
		while(true) {
			
			kg.nannyEnter();

			try {
				sleep((int) (Math.random() * 2000 + 1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			kg.nannyExit();

		}
	}
}
