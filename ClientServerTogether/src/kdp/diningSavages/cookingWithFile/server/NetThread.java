package kdp.diningSavages.cookingWithFile.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import kdp.diningSavages.multiplePots.bothSide.NetPot;
import kdp.diningSavages.multiplePots.bothSide.Pot;

public class NetThread extends Thread {

	private int port;
	private NetPot pot;
	
	
	public NetThread(Pot pot, int portNet) {
		port = portNet;
		this.pot = (NetPot) pot;
	}
	
	@Override
	public void run() {
		
		try(FileWriter fw = new FileWriter(Server.PATH);
			PrintWriter pw = new PrintWriter(fw,true)){
			
			fw.write("Pocetak kuvanja");
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try(ServerSocket server = new ServerSocket(port)){
			System.out.println("Server can accept messages at port: " + port);
			
			while(true) {
				try(Socket client = server.accept();
					ObjectInputStream in = new ObjectInputStream(client.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())){
					
					String op = (String) in.readObject();
					if(!("EAT".equals(op))) {
						System.out.println("ERROR");
					}
					// this is eat!
					int type = in.readInt();
					
					pot.eat(type);
					
					
					try(FileReader fr = new FileReader(Server.PATH);
						BufferedReader br = new BufferedReader(fr)) {
						
						String s;
						while((s = br.readLine()) != null){
							out.writeObject(s + "\n");
						}
						out.writeObject("null");
					} catch (Exception e) {
						e.printStackTrace();
					}					
					
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
