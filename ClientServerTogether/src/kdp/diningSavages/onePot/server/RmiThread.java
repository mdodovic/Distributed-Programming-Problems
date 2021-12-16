package kdp.diningSavages.onePot.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import kdp.diningSavages.onePot.bothSide.Pot;
import kdp.diningSavages.onePot.bothSide.RemotePot;

public class RmiThread extends Thread{

	public RmiThread(int port, Pot pot) {
		try {
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			RemotePot rPot = new RemotePotImpl(pot);
			RemotePot stub = (RemotePot) UnicastRemoteObject.exportObject(rPot, 0);

			Registry reg = LocateRegistry.createRegistry(port);
			
			reg.bind("/pot", stub);
			System.out.println("Server ready to accept remopte procedure calls on port: " + port);
		
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

}
