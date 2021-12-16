package kdp.diningSavages.onePot.server;

import kdp.diningSavages.onePot.bothSide.Pot;

public class ServerPot {

	
	public static void main(String[] args) {
		
		int portRmi = 5555; 
		int portNet = 6666; 
		
		Pot pot = new SemaphorePot(); // shared pot
		
		new RmiThread(portRmi, pot).start();
		new NetThread(portNet, pot).start();
		
		
		
	}
}
