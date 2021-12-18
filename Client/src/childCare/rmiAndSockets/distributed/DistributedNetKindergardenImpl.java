package childCare.rmiAndSockets.distributed;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DistributedNetKindergardenImpl implements DistributedNetKindergarden{
	
	private int port;
	private String host;
	public DistributedNetKindergardenImpl(int port, String host) {
		this.port = port;
		this.host = host;
	}

	@Override
	public void nannyEnter() {

		try(Socket c = new Socket(host, port);
			ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(c.getInputStream())){
			
			out.writeObject("nannyEnter");
			in.readObject();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void nannyExit() {
		try(Socket c = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(c.getInputStream())){
				
				out.writeObject("nannyExit");
				in.readObject();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	





	
}
