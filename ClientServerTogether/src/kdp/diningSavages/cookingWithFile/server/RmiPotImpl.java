package kdp.diningSavages.cookingWithFile.server;

import java.rmi.RemoteException;

import kdp.diningSavages.multiplePots.bothSide.Pot;
import kdp.diningSavages.multiplePots.bothSide.RmiPot;

public class RmiPotImpl implements RmiPot{

	private RmiPot pot;
	
	public RmiPotImpl(Pot pot) {
		this.pot = (RmiPot) pot;
	}

	@Override
	public int cook() throws RemoteException {

		return pot.cook();
	}
}
