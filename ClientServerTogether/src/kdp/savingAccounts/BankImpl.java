package kdp.savingAccounts;

import java.rmi.RemoteException;
import java.util.HashMap;

public class BankImpl implements Bank{

	
	private static transient HashMap<String, UserAccount> users = new HashMap<String, UserAccount>();

	
	@Override
	public UserAccount getUserAccount(String name) throws RemoteException {
		
		synchronized (this) {
			UserAccount user = users.get(name);
			if(user == null) {
				user = new UserAccountImpl(name);
				users.put(name, user);
			}
			return user;
		}

	}

	
}
