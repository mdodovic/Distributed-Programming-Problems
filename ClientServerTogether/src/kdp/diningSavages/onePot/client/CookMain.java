package kdp.diningSavages.onePot.client;


import kdp.diningSavages.onePot.bothSide.RemotePot;

public class CookMain {

	public static void main(String[] args) {
		int port = 5555;
		String host = "localhost";
		
		RemotePot pot = new RmiPot(host, port);
		
		new CookGui(pot);
	}

}
