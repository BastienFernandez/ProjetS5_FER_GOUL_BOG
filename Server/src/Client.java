import java.net.Socket;

/**
 * 
 */

/**
 * @author corentinboget
 *
 */
public class Client {

	private String name = "Unknow";
	private Socket socket = null;
	
	/**
	 * @param name
	 * @param socket
	 */
	public Client(String name, Socket socket) {
		super();
		this.name = name;
		this.socket = socket;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}
	
	
}
