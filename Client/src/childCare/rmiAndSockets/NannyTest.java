package childCare.rmiAndSockets;

import childCare.rmiAndSockets.distributed.DistributedNetKindergarden;
import childCare.rmiAndSockets.distributed.DistributedNetKindergardenImpl;

public class NannyTest {

	public static final int NUM_NANNIES = 3;
	
	public static void main(String[] args) {

		String host = "localhost";
		int port = 6666;

		DistributedNetKindergarden kg = new DistributedNetKindergardenImpl(port, host);
		
		for(int i = 0; i < NUM_NANNIES; i++)
			new Nanny(kg, i).start();
			

	}

}
