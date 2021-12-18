package childCare.rmiAndSockets;

import java.rmi.RemoteException;

import childCare.rmiAndSockets.distributed.DistributedRmiKindergarden;

public class Parent extends Thread {

	DistributedRmiKindergarden kg;
	
	public Parent(DistributedRmiKindergarden kg, int i) {
		super("P" + i);
		this.kg = kg;

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
