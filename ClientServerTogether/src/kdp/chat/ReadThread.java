package kdp.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ReadThread extends Thread {

	private Socket client;
	
	public ReadThread(Socket s) {
		client = s;
	}

	@Override
	public void run() {
		try(Socket client = this.client;
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))){
			String s;
			while ((s = in.readLine()) != null ) {
				System.out.println("> " + s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
