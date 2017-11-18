package gui;
/**
 * 
 */

/**
 * @author corentinboget
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private JLabel label_serverStatus = new JLabel("Status : Off");
	private JLabel label_nbClientConnected = new JLabel("Clients connected : 0");
	private JLabel label_nbClientSubscribed = new JLabel("Clients subsribed : 0");
	private int nbClientsConnected = 0;
	
	public MainWindow(){
		this.setTitle("Server");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setBackground(Color.WHITE);
		container.setLayout(new BorderLayout());
			
			JTabbedPane tabPane = new JTabbedPane();
		
				//SERVER STATUS
				JPanel panel_server_status = new JPanel();
					
				panel_server_status.setLayout(new BoxLayout(panel_server_status, BoxLayout.Y_AXIS));
				
						Font labelsFont = new Font("Arial", Font.BOLD, 14);
						label_serverStatus.setFont(labelsFont);
						label_serverStatus.setForeground(Color.GRAY);
						label_nbClientConnected.setFont(labelsFont);
						label_nbClientConnected.setForeground(Color.GRAY);
						label_nbClientSubscribed.setFont(labelsFont);
						label_nbClientSubscribed.setForeground(Color.GRAY);
								
				panel_server_status.add(label_serverStatus);
				panel_server_status.add(label_nbClientConnected);
				panel_server_status.add(label_nbClientSubscribed);
				
				//CLIENT LIST
				JPanel panel_clientsData = new JPanel();
				
				
				
				//TICKET LIST
				JPanel panel_tickets = new JPanel();
				
				
				
			tabPane.addTab("Status", null, panel_server_status, "Server status");
			tabPane.addTab("Clients", null, panel_clientsData, "Clients data");
			tabPane.addTab("Tickets", null, panel_tickets, "Tickets open");
			
		container.add(tabPane);
				
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	public void serverListening(){
		label_serverStatus.setText("Status : On");
	}
	
	public void clientConnection(){
		nbClientsConnected++;
		label_nbClientConnected.setText("Clients connected : " + nbClientsConnected);
	}
	
	public void clientDeconnection(){
		nbClientsConnected--;
		label_nbClientConnected.setText("Clients connected : " + nbClientsConnected);
	}
}
