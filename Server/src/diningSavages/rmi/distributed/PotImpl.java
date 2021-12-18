package diningSavages.rmi.distributed;

import java.rmi.RemoteException;

import diningSavages.rmi.concurrent.DiningSavages;

public class PotImpl implements Pot {

	private DiningSavages pot;
	
	public PotImpl(DiningSavages dsPot) {
		this.pot = dsPot;
	}
	
	@Override
	public int cook() throws RemoteException {
		return pot.cook();
	}

	@Override
	public void eat(int type) throws RemoteException {
		pot.eat(type);
	}

	
	
}
