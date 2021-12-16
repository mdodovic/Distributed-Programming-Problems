package kdp.diningSavages.cookingWithFile.bothSide;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiPot extends Remote, Pot{

	int cook() throws RemoteException;
	
}
