package diningSavages.sockets.distributed;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import diningSavages.sockets.concurrent.DiningSavages;
import diningSavages.sockets.concurrent.SemaphorePot;

public class Server {

	public static final int NUM_FOOD_TYPES = 4;
	public static final int MAX_FOOD_IN_POT = 3;
	
	public static final String PATH_TO_SERVER_FILE = "C:\\Users\\Matija\\Desktop\\Fakultet\\trecaGodina\\KDP\\lab\\DistributedProgrammingServer\\src\\diningSavages\\sockets\\concurrent\\serverFile.txt";
	
	private static void run(int port) {
		
		try (ServerSocket server = new ServerSocket(port)){
			DiningSavages pot = new SemaphorePot(NUM_FOOD_TYPES, MAX_FOOD_IN_POT, PATH_TO_SERVER_FILE);
			System.out.println("Server is ready to accept messages at port: " + port);
			while(true) {
				
				Socket client = server.accept();
				
				new WorkingThread(pot, client).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		int port = 5555;
		
		run(port);
		
	}
	
}
