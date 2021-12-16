package kdp.diningSavages.onePot.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import kdp.diningSavages.onePot.bothSide.NetPotI;

public class NetPot implements NetPotI{

	private String host;
	private int port;
	
	public NetPot(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	@Override
	public void eat() {
		try(Socket client = new Socket(host, port);
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())){
			
			out.writeObject("EAT");
			in.readObject(); // ACK
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	
	
}
