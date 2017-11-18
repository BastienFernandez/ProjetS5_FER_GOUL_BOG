/**
 * 
 */

/**
 * @author corentinboget
 *
 */

import java.io.IOException;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Set;
import java.util.TreeSet;

import gui.MainWindow;

public class Server {

	private ServerSocket socket = null;
	private int port = 5555;
	private boolean isRunning = false;
	private static int max_client = 100;
	private Set<Client> clients = new TreeSet(); 
	private MainWindow window = new MainWindow();
	
	public Server(){
		try {
			socket = new ServerSocket(port, max_client, InetAddress.getByName("127.0.0.1"));
			isRunning = true;
			System.out.println("Server is running on port " + port);
			window.serverListening();
			
			while(isRunning){
				
				try {
					Socket client = socket.accept();			
		
					System.out.println("Connection receive");
					window.clientConnection();
					
					Thread thread = new Thread(new ClientRunner(client, window));
					thread.start();
					
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				socket = null;
			}
			
		} catch (IOException e){
			System.out.println("Unable to lauch the server...");
		}
	}
	
	public void close(){
		isRunning = false;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
}
