package diningSavages.sockets;

import diningSavages.sockets.distributed.DiningSavages;
import diningSavages.sockets.distributed.RemoteDiningSavages;


public class SavageMain {
	public static final int NUM_SAVAGES = 5;
	public static final int NUM_FOOD_TYPES = 4;

	public static final String PATH_TO_FOLDER = "C:\\Users\\Matija\\Desktop\\Fakultet\\trecaGodina\\KDP\\lab\\DistributedProgrammingClient\\src\\diningSavages\\sockets\\";
	
	public static void main(String[] args) {
		
		String  host = "localhost";
		int port = 5555;

		
		DiningSavages pot = new RemoteDiningSavages(host, port);
		
		for(int i = 0; i < NUM_SAVAGES; i++) {
			new Savage(pot, i);
		}
	
	}

	
}
