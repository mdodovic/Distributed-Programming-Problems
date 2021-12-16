package kdp.diningSavages.onePot.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import kdp.diningSavages.onePot.bothSide.NetPotI;
import kdp.diningSavages.onePot.bothSide.Pot;

public class NetThread extends Thread {

	private int port;
	private NetPotI pot;
	
	public NetThread(int port, Pot pot) {
		this.port = port;
		this.pot = (NetPotI) pot;
		
	}
	
	@Override
	public void run() {
		try(ServerSocket server = new ServerSocket(port)){
			System.out.println("Server ready to accept messages on port: " + port);
			while(true) {
				try(Socket client = server.accept();
						ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
						ObjectInputStream in = new ObjectInputStream(client.getInputStream())){
					
					String operation = (String) in.readObject();
					
					if("EAT".equals(operation)) {
						pot.eat();
						out.writeObject("ACK");
					} else {
						System.err.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");		
					}
					
				}catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
