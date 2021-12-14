package kdp.savingAccounts;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserAccountImpl extends UnicastRemoteObject implements UserAccount, Serializable{

	private static final long serialVersionUID = 1L;
	
	private float status = 0;
	private String name;
	
	public UserAccountImpl(String name) throws RemoteException{
		this.name = name;
	}

	@Override
	public float getStatus() throws RemoteException {
		synchronized (this) {
			return status;			
		}
	}

	@Override
	public void transaction(float value) throws RemoteException {
		synchronized (this) {
			while(status + value < 0) {
				try { this.wait(); } catch (InterruptedException e) {}
			}
			status += value;
			notifyAll();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
