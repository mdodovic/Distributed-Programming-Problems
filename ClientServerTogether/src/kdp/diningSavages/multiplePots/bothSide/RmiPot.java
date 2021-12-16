package kdp.diningSavages.multiplePots.bothSide;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiPot extends Remote, Pot{

	int cook() throws RemoteException;
	
}
