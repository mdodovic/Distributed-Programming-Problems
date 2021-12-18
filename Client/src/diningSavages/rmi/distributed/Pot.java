package diningSavages.rmi.distributed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Pot extends Remote{
	
	int cook() throws RemoteException;
	void eat(int type) throws RemoteException;
	
}
