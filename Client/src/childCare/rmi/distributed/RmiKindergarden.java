package childCare.rmi.distributed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiKindergarden extends Remote{
	
	
	boolean bringChildren(int numOfChildren) throws RemoteException;
	void takeChildren(int numOfChildren) throws RemoteException;
	
	void nannyEnter() throws RemoteException;
	void nannyExit() throws RemoteException;
	
}