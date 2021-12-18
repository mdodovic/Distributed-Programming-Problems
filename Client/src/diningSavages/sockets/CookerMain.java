package diningSavages.sockets;

import diningSavages.sockets.distributed.DiningSavages;
import diningSavages.sockets.distributed.RemoteDiningSavages;


public class CookerMain {

	
	public static void main(String[] args) {
		
		String  host = "localhost";
		int port = 5555;
	
		DiningSavages pot = new RemoteDiningSavages(host, port);
		
		new Cook(pot);
		
	}
}
