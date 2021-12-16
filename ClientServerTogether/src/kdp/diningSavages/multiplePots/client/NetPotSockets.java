package kdp.diningSavages.multiplePots.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import kdp.diningSavages.multiplePots.bothSide.NetPot;

public class NetPotSockets implements NetPot {

	private String host;
	private int port;
	public NetPotSockets(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void eat(int type) {
		try(Socket client = new Socket(host, port);				
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(client.getInputStream())){
			
			out.writeObject("EAT");
			out.writeInt(type);
			out.flush();
			
			in.readObject();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
