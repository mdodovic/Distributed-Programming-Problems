package kdp.diningSavages.multiplePots.client;

import kdp.diningSavages.multiplePots.bothSide.NetPot;

public class SavageMain {

	public static final int NUM_SAVAGES = 5;
	
	public static void main(String[] args) {
		
		String host = "localhost";
		int port = 6666;
		
		NetPot pot = new NetPotSockets(host, port);
		
		for(int i = 0; i < NUM_SAVAGES; i++)
			new SavageGui(pot, i);
	}
}
