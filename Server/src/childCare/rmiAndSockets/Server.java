package childCare.rmiAndSockets;

import childCare.rmiAndSockets.concurrent.Kindergarden;
import childCare.rmiAndSockets.concurrent.SemaphoreKindergarden;

public class Server {

	public static void main(String[] args) {
		
		int portRmi = 5555;
		int portNet = 6666;
		
		Kindergarden kg = new SemaphoreKindergarden();
		
		new NetThread(portNet, kg).start();
		new RmiThread(portRmi, kg).start();
		
	}
	
}
