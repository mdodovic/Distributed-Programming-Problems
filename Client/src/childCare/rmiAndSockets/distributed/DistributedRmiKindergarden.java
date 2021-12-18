package childCare.rmiAndSockets.distributed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DistributedRmiKindergarden extends Remote{

	boolean bringChildren(int numOfChildren) throws RemoteException;
	void takeChildren(int numOfChildren) throws RemoteException;
	
}
