package kdp.atomicBroadcast.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import kdp.atomicBroadcast.concurrentPart.AtomicBroadcastBuffer;

public class ClientNetAtomicBroadcastBuffer<T> implements AtomicBroadcastBuffer<T> {

	private String host;
	private int port;

	
	public ClientNetAtomicBroadcastBuffer(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int id) {
		T item= null;
		
		try(Socket client = new Socket(host, port);
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
			
			out.writeObject("get");
			out.writeInt(id);
			out.flush();
			
			item = (T) in.readObject();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public void put(T item) {
		try(Socket client = new Socket(host, port);
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(client.getInputStream())){

			out.writeObject("put");
			out.writeObject(item);
			
			in.readObject();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	}

}
