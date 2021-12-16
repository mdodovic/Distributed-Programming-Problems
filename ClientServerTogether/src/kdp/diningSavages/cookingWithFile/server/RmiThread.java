package kdp.diningSavages.cookingWithFile.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import kdp.diningSavages.multiplePots.bothSide.Pot;
import kdp.diningSavages.multiplePots.bothSide.RmiPot;

public class RmiThread extends Thread {

	
	
	public RmiThread(Pot pot, int portRmi) {
		try {
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			RmiPot potObj = new RmiPotImpl(pot);
			RmiPot potStub = (RmiPot) UnicastRemoteObject.exportObject(potObj, 0);
			
			Registry reg = LocateRegistry.createRegistry(portRmi);
			reg.bind("/pot", potStub);
			System.out.println("Server can accept remote procedure invocations at port: " + portRmi);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}	
		
	}

}
