package cocoonClient;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class ChatClient extends JFrame {

	private String destinationIPAddr;
	private int destinationPortNum;
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private String nickname;
	private JTextArea textArea;
	private JTextField textField;
	
	public ChatClient() {
		
		/*this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize textArea
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		this.textArea.setPreferredSize(new Dimension(500,550));
		JScrollPane scrollPane = new JScrollPane(this.textArea);
	    this.add(scrollPane);
	    
	    // Initialize textField
	    this.textField = new JTextField();
	    this.textField.setPreferredSize(new Dimension(500,40));
	    this.textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChatClient.this.sendMessage(ChatClient.this.textField.getText());
			}
	    	
	    });
	    this.add(this.textField);
	    
	    this.pack();
	    
	    // Ask for nickname before showing client window
	    this.nickname = JOptionPane.showInputDialog("Nickname", "Unknown");
		this.welcome(this.nickname);
		
		this.setVisible(true);
		
//		System.out.println(SwingUtilities.isEventDispatchThread());
 
 */
	}
	
	public ChatClient(String IPAddress, int portNum) {
		this();
		this.destinationIPAddr = IPAddress;
		this.destinationPortNum = portNum;
	}
	
	/*private void welcome(final String nickname) {

		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
//				System.out.println(SwingUtilities.isEventDispatchThread());
				StringBuilder sBuilder = new StringBuilder("**[ Welcome ");
				sBuilder.append(nickname).append("! ]\n");
				ChatClient.this.textArea.append(sBuilder.toString());
			}
			
		});
	}*/
	
	public void sendMessage(String message) {
//		System.out.println(SwingUtilities.isEventDispatchThread());
	
		this.writer.println(message);
		this.writer.flush();
		//this.textField.setText("");
	}
	public String getIPAddress(){
		return this.destinationIPAddr;
	}
	public int getPortNum(){
		return this.destinationPortNum;
	}
	public void setIPAddress(String IPAddress) {
		this.destinationIPAddr = IPAddress;
	}
	public boolean isConnected(){
		return writer != null;
	}
	public void setPort(int portNum) {
		this.destinationPortNum = portNum;
	}
	
	public void connect() {
		// Create socket & thread, remember to start your thread
		try {
			reader = null;
			writer = null;
			socket = null;
			Thread thread = new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						socket = new Socket(destinationIPAddr, destinationPortNum);
					} catch (Exception e) {	}
				}
				
			});
			thread.start();
			thread.join(500);
			thread.stop();
			System.out.println(socket);
			new ClientThread(socket);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/*private void addLine(final String message) {
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				ChatClient.this.textArea.append(message+"\n"); 
			}
			
		});
	}*/
	
	// Define an inner class for handling message reading
	public class ClientThread extends Thread{
		//private Socket socket;
		public ClientThread(Socket socket){
			try {
				writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void sendMessage(String msg){
			ChatClient.this.writer.println(msg);
			ChatClient.this.writer.flush();
		}
		/*@Override
		public void run(){
			while(true){	
				try {
					String line = reader.readLine();
					System.out.println(line);
					ChatClient.this.addLine(line);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}*/
		
	}
	

}
