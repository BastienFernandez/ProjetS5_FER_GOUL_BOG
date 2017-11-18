/**
 * 
 */

/**
 * @author corentinboget
 *
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import gui.MainWindow;

public class ClientRunner implements Runnable {

	private Socket socket;
	private MainWindow window;
	private ObjectOutputStream writer = null;
	private ObjectInputStream reader = null;
	
	public class Response {
		public static final String DISCONECTION = "DISCONECTION";
		public static final String TEXT = "TEXT";
	}
	
	public class Request {
		public static final String TEXT = "TEXT";
	}
	
	public ClientRunner(Socket socket, MainWindow window) {
		this.socket = socket; 
		this.window = window;
	}
	
	@Override
	public void run() {
		
		boolean closeConnection = false;

		System.out.println("Starting client connection process.");
		sendData(Request.TEXT, "Hello buddy !");
		
		while(!socket.isClosed()){
			try {	
				
				writer = new ObjectOutputStream(socket.getOutputStream());
				reader = new ObjectInputStream(socket.getInputStream());
				
				Message res = null;
				res = (Message) reader.readObject();
				
				String debug = "Client : " + Thread.currentThread().getName() + ". ";
				debug += " -> Commande reçue : " + res.type + "\n";
				System.out.println("\n" + debug);
				
				switch(res.type){ // DATA TYPE
					case Response.DISCONECTION: 
						closeConnection = true;
						break;
				}
								
				if(closeConnection){
					System.out.println("Client : " + Thread.currentThread().getName() + " Deconnection.");
					writer = null;
					reader = null;
					window.clientDeconnection();
					socket.close();
					break;
				}	
				
			} catch(SocketException e){
				System.out.println("Client : " + Thread.currentThread().getName() + " Connection interupted.");
				window.clientDeconnection();
				break;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendData(String type, String message){
		try {
			writer = new ObjectOutputStream(socket.getOutputStream());
			Message messageToSend = new Message();
			messageToSend.type = type;
			messageToSend.source = "Server";
			messageToSend.data = message;
			writer.writeObject(messageToSend);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
