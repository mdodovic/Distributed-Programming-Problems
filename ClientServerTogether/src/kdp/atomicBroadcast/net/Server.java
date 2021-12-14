package kdp.atomicBroadcast.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import kdp.atomicBroadcast.concurrentPart.AtomicBroadcastBuffer;
import kdp.atomicBroadcast.concurrentPart.ConcurrentAtomicBroadcastBuffer;
import kdp.atomicBroadcast.message.Message;

public class Server {

	private static void run(int port) {
		System.out.println("Server starts!");
		
		AtomicBroadcastBuffer<Message<String>> buffer = new ConcurrentAtomicBroadcastBuffer<Message<String>>(5,3);		

		try(ServerSocket server = new ServerSocket(port)){
			while(true) {
				
				Socket client = server.accept();
				new WorkingThread(client, buffer).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		run(5555);
	}

}
