package kdp.savingAccounts;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BankServer {

	public static void main(String[] args) {
		
		try {
			int port = 5555;
			
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			Bank bank = new BankImpl();
			Bank stub = (Bank) UnicastRemoteObject.exportObject(bank, 0);
			
			Registry reg = LocateRegistry.createRegistry(port);
			
			reg.rebind("/bank", stub);	
			
			System.out.println("Bank is running!");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
