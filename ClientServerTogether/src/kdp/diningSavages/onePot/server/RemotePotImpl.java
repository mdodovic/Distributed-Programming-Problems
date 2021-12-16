package kdp.diningSavages.onePot.server;

import java.rmi.RemoteException;

import kdp.diningSavages.onePot.bothSide.Pot;
import kdp.diningSavages.onePot.bothSide.RemotePot;

public class RemotePotImpl implements RemotePot{

	private SemaphorePot pot;

	public RemotePotImpl(Pot pot) {
		this.pot = (SemaphorePot) pot;
	}

	@Override
	public void cook() throws RemoteException {
		pot.cook();		
	}
	
}
