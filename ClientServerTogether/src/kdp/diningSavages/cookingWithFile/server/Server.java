package kdp.diningSavages.cookingWithFile.server;

import kdp.diningSavages.multiplePots.bothSide.Pot;

public class Server {
	
	public static final String PATH = "serverFile.txt";
	public static void main(String[] args) {
		
		int portRmi = 5555;
		int portNet = 6666;
		
		Pot pot = new SemaphorePot(4, 3);
		
		new RmiThread(pot, portRmi).start();
		new NetThread(pot, portNet).start();
		
		
	}

}
