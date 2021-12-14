package kdp.chat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		
		String host = "localhost";
		int port = 5555;
		
		try(Socket server = new Socket(host, port)){
			
			Chat c = new Chat(server);
			c.communicate();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
