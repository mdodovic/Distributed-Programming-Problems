package kdp.chat;

import java.io.IOException;
import java.net.Socket;

public class Chat {

	private Socket client;
	
	public Chat(Socket client) {
		this.client = client;
	}

	public void communicate() {
		try(Socket s = this.client){
			
			ReadThread reader = new ReadThread(s);
			WriteThread writer = new WriteThread(s);
			
			reader.start();
			writer.start();
			reader.join();
			writer.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

}
