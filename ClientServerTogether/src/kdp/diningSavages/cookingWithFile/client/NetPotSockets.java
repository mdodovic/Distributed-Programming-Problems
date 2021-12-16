package kdp.diningSavages.cookingWithFile.client;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import kdp.diningSavages.multiplePots.bothSide.NetPot;

public class NetPotSockets implements NetPot {

	private String host;
	private int port;
	
	private static int cnt = 0;
	
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
			
			
			try (FileWriter f = new FileWriter("savage " + cnt + ".txt"); 
				PrintWriter p = new PrintWriter(f,true)) { 
				
				
				String s = "";
				while(!"null".equals(s)){
					s = (String) in.readObject();
					p.write(s);
				}
								
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			cnt++;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
