package childCare.rmiAndSockets.distributed;

import java.rmi.RemoteException;

import childCare.rmiAndSockets.concurrent.Kindergarden;

public class DistributedRmiKindergardenImpl implements DistributedRmiKindergarden {

	Kindergarden kg;
	
	public DistributedRmiKindergardenImpl(Kindergarden kg) {
		this.kg = kg;
	}

	@Override
	public boolean bringChildren(int numOfChildren) throws RemoteException {
		return kg.bringChildren(numOfChildren);
	}

	@Override
	public void takeChildren(int numOfChildren) throws RemoteException {
		kg.takeChildren(numOfChildren);
	}

}
