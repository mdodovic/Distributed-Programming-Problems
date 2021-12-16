package kdp.diningSavages.onePot.client;

import kdp.diningSavages.onePot.bothSide.NetPotI;

public class SavageMain {

	public static final int NUM_SAVAGES = 5;
	
	public static void main(String[] args) {
		int port = 6666;
		String host = "localhost";
		
		NetPotI pot = new NetPot(host, port);
		
		SavageGui[] savages = new SavageGui[NUM_SAVAGES];
		for(int i = 0; i < NUM_SAVAGES; i++)
			savages[i] = new SavageGui(pot, i);
	}

}
