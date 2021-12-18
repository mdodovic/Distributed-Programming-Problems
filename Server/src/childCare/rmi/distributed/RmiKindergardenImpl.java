package childCare.rmi.distributed;

import java.rmi.RemoteException;

import childCare.rmi.concurrent.Kindergarden;

public class RmiKindergardenImpl implements RmiKindergarden {

	private Kindergarden kindergarden;

	
	public RmiKindergardenImpl(Kindergarden kindergarden) {
		this.kindergarden = kindergarden;
	}

	@Override
	public boolean bringChildren(int numOfChildren) throws RemoteException {
		return kindergarden.bringChildren(numOfChildren);
	}

	@Override
	public void takeChildren(int numOfChildren) throws RemoteException {
		kindergarden.takeChildren(numOfChildren);
	}

	@Override
	public void nannyEnter() throws RemoteException {
		kindergarden.nannyEnter();
	}

	@Override
	public void nannyExit() throws RemoteException {
		kindergarden.nannyExit();
	}
	
	

}
