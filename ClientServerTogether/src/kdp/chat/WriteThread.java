package kdp.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WriteThread extends Thread {

	private Socket client;
	
	public WriteThread(Socket s) {
		client = s;
	}

	@Override
	public void run() {
		try(Socket client = this.client;
			PrintWriter out = new PrintWriter(client.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in))){

			String s;
			while (!out.checkError() && ((s = in.readLine()) != null )) {
				out.println(s);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
