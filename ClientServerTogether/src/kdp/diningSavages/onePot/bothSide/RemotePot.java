package kdp.diningSavages.onePot.bothSide;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemotePot extends Remote, Pot{

	public void cook() throws RemoteException;
	
}
