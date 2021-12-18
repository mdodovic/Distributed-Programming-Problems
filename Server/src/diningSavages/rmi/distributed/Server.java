package diningSavages.rmi.distributed;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import diningSavages.rmi.concurrent.DiningSavages;
import diningSavages.rmi.concurrent.SemaphorePot;


public class Server {


	public static final int NUM_FOOD_TYPES = 4;
	public static final int MAX_FOOD_IN_POTS = 3;
	
	public static void main(String[] args) {
		int port = 5555;
		
		DiningSavages dsPot = new SemaphorePot(NUM_FOOD_TYPES, MAX_FOOD_IN_POTS);
		
		try {
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			Pot pot = new PotImpl(dsPot);
			Pot stub = (Pot) UnicastRemoteObject.exportObject(pot, 0);
			
			Registry reg = LocateRegistry.createRegistry(port);
			
			reg.bind("/pot", stub);
			
			System.out.println("Server ready to accept invocations at port: " + port);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}

	}

}
