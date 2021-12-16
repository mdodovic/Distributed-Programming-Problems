package kdp.diningSavages.cookingWithFile.client;

import kdp.diningSavages.multiplePots.bothSide.RmiPot;

public class CookMain {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 5555;
		
		RmiPot pot = new RmiPotInvocation(host, port);
		
		new CookGui(pot);
	}

}
