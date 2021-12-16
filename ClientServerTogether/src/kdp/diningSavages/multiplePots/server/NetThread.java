package kdp.diningSavages.multiplePots.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import kdp.diningSavages.multiplePots.bothSide.NetPot;
import kdp.diningSavages.multiplePots.bothSide.Pot;

public class NetThread extends Thread {

	private int port;
	private NetPot pot;
	
	
	public NetThread(Pot pot, int portNet) {
		port = portNet;
		this.pot = (NetPot) pot;
	}
	
	@Override
	public void run() {
		try(ServerSocket server = new ServerSocket(port)){
			System.out.println("Server can accept messages at port: " + port);
			
			while(true) {
				try(Socket client = server.accept();
					ObjectInputStream in = new ObjectInputStream(client.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())){
					
					String op = (String) in.readObject();
					if(!("EAT".equals(op))) {
						System.out.println("ERROR");
					}
					// this is eat!
					int type = in.readInt();
					
					pot.eat(type);
					
					out.writeObject("ACK");
					
				} catch (IOException e) {
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
