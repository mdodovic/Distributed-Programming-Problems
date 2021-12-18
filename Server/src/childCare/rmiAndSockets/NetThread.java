package childCare.rmiAndSockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import childCare.rmiAndSockets.concurrent.Kindergarden;

public class NetThread extends Thread {

	private int port;
	private Kindergarden kg;
	
	public NetThread(int port, Kindergarden kg) {
		this.port = port;
		this.kg = kg;
	}

	@Override
	public void run() {
		
		try(ServerSocket server = new ServerSocket(port)){
			System.out.println("Server ready to accept messages at port: " + port);
			
			while(true) {
				
				Socket client = server.accept();
				
				new WorkingThread(kg, client).start();
				
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
