package diningSavages.sockets.distributed;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

import diningSavages.sockets.concurrent.DiningSavages;

public class WorkingThread extends Thread {

	private DiningSavages pot;
	private Socket client;
	
	public WorkingThread(DiningSavages pot, Socket client) {
		this.pot = pot;
		this.client = client;
	}

	@Override
	public void run() {
		
		try(Socket s = this.client;
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			ObjectOutput out = new ObjectOutputStream(s.getOutputStream())
				){
			
			String op = (String) in.readObject();
			
			if("cook".equals(op)) {
				int retType = pot.cook();
				
				out.writeInt(retType);
				out.flush();
				
			} else if("eat".equals(op)) {
				
				int eatType = in.readInt();
				pot.eat(eatType);
				
				out.writeObject("fileToAccept");
				
				try(FileReader f = new FileReader(Server.PATH_TO_SERVER_FILE);
					BufferedReader br = new BufferedReader(f)){
						
					String line;
					while( (line = br.readLine()) != null) {
						out.writeObject(line);
					}
					out.writeObject("null");
					} catch (IOException e) {
						e.printStackTrace();
				}
				
				out.writeObject("ACK");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
