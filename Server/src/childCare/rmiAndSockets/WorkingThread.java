package childCare.rmiAndSockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import childCare.rmiAndSockets.concurrent.Kindergarden;

public class WorkingThread extends Thread {

	private Kindergarden kg;
	private Socket client;

	public WorkingThread(Kindergarden kg, Socket client) {
		this.kg = kg;
		this.client = client;
	}
	
	@Override
	public void run() {
		try(Socket c = this.client;
			ObjectInputStream in = new ObjectInputStream(c.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream())){

			String op = (String) in.readObject();
			if("nannyEnter".equals(op)) {
				kg.nannyEnter();
			} else {
				kg.nannyExit();
			}
			
			out.writeObject("ACK");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
