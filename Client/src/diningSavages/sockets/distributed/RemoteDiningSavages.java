package diningSavages.sockets.distributed;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import diningSavages.sockets.SavageMain;

public class RemoteDiningSavages implements DiningSavages{
	
	private String host;
	private int port;
	
	private static int cnt = 1;
	
	public RemoteDiningSavages(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public int cook() {
		int type = 0;
		try(Socket client = new Socket(host, port);
			ObjectOutput out = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(client.getInputStream())){
			
			out.writeObject("cook");
			type = in.readInt();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return type;
	}

	@Override
	public void eat(int type) {
		try(Socket client = new Socket(host, port);
			ObjectOutput out = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(client.getInputStream())){
			
			out.writeObject("eat");
			out.writeInt(type);
			out.flush();
			
			String ack = (String) in.readObject();
			if(ack.equals("fileToAccept")) {
			
				try(FileWriter f = new FileWriter(SavageMain.PATH_TO_FOLDER + "fileClient_T" + type + "_I"+cnt + ".txt");
					PrintWriter pw = new PrintWriter(f)){
					
					String line;
					while( !(line = (String) in.readObject()).equals("null")) {
						pw.write(line + "\n");
					}

				}
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
