package childCare.rmi;

import java.rmi.RemoteException;

import childCare.rmi.distributed.RmiKindergarden;

public class Nanny extends Thread {

	RmiKindergarden kg;
	
	public Nanny(RmiKindergarden kindergarden, int i) {
		super("N" + i);
		kg = kindergarden;

	}
	
	@Override
	public void run() {
		while(true) {
			
			try {
				kg.nannyEnter();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

			try {
				sleep((int) (Math.random() * 2000 + 1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				kg.nannyExit();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}
