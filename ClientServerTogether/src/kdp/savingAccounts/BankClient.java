package kdp.savingAccounts;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BankClient {

	public static void main(String[] args) {
		int port = 5555;
		String host = "localhost";
		
		try {
			
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			Registry reg = LocateRegistry.getRegistry(host, port);
			
			Bank bank = (Bank) reg.lookup("/bank");
			
			UserAccount account = bank.getUserAccount("Mica");
			
			for (int m = 0; m < 100; m++) {
				float newStatus = (float)(50 + m - (int)(Math.random() * 100));
				
				try {
					System.out.println("Status: " + account.getStatus());
					System.out.println("Changed for: " + newStatus);
					account.transaction(newStatus);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		}		
		
	}
	
}
