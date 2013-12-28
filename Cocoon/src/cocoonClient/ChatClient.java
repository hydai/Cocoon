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

import JSONTransmitProtocol.reader.JSONReader;


public class ChatClient{

	private String destinationIPAddr;
	private int destinationPortNum;
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private String nickname;
	private JTextArea textArea;
	private JTextField textField;
	private MainFrame parent;
	public ChatClient() {
		
	}
	
	public ChatClient(MainFrame parent, String IPAddress, int portNum) {
		this();
		this.parent = parent;
		this.destinationIPAddr = IPAddress;
		this.destinationPortNum = portNum;
	}
	
	public void sendMessage(String message) {
		this.writer.println(message);
		this.writer.flush();
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
			new ClientThread(socket).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
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
		@Override
		public void run(){
			while(true){
				String line = "", response = "";
				try{
					line = reader.readLine();
					if(line == null)
						break;
					if(new JSONReader(line).getType() == "status"){
						response = new JSONReader(line).getStatus().getResult();
						System.out.println("Resopnse:" + response);
						JOptionPane.showMessageDialog(parent, "response: "+ response);
					}
				}
				catch(Exception e){
					break;
				}
			}
			System.out.println("Disconnect");
		}
	}
}
