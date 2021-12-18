package childCare.rmi;

import java.rmi.RemoteException;

import childCare.rmi.distributed.RmiKindergarden;

public class Parent extends Thread {

	RmiKindergarden kg;
	
	public Parent(RmiKindergarden kindergarden, int i) {
		super("P" + i);
		kg = kindergarden;

	}
	
	@Override
	public void run() {
		while(true) {
			int numOfChildren = (int) (Math.random() * 4 + 1);
			try {
				if(!kg.bringChildren(numOfChildren)) {
					try {
						sleep((int) (Math.random() * 3000 + 1));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;				
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			try {
				sleep((int) (Math.random() * 1000 + 1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				kg.takeChildren(numOfChildren);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

}
