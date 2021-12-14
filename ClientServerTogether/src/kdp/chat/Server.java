package kdp.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		int port = 5555;
		
		try(ServerSocket server = new ServerSocket(port)){
			Socket client = server.accept();
			Chat c = new Chat(client);
			c.communicate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
