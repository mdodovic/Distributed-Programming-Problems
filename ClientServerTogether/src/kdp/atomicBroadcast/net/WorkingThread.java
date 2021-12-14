package kdp.atomicBroadcast.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import kdp.atomicBroadcast.concurrentPart.AtomicBroadcastBuffer;
import kdp.atomicBroadcast.message.Message;

public class WorkingThread extends Thread {

	private Socket c;
	private AtomicBroadcastBuffer<Message<String>> buffer;
	
	public WorkingThread(Socket client, AtomicBroadcastBuffer<Message<String>> buffer) {
		c = client;
		this.buffer = buffer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		
		try(Socket client = this.c;
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())){
			
			String operation = (String) in.readObject();
			switch (operation) {
				case "put": {
					Message<String> msg = (Message<String>) in.readObject();							
					buffer.put(msg);
					out.writeObject("ACK");
					break;
				}
				case "get": {
					int id = in.readInt();
					Message<String> msg = buffer.get(id);
					out.writeObject(msg);
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + operation);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
