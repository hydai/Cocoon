package cocoonServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

	private ServerSocket serverSocket;
	private List<ConnectionThread> connections = new ArrayList<ConnectionThread>();
	
	public Server(int portNum) {
		try {
			this.serverSocket = new ServerSocket(portNum);
			System.out.printf("Server starts listening on port %d.\n", portNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void runForever() {
		System.out.println("Server starts waiting for client.");
		// Create a loop to make server wait for client forever (unless you stop it)
		// Make sure you do create a connectionThread and add it into 'connections'
		while (true) {
			try {
				Socket connectionToClient = this.serverSocket.accept();
				System.out.println("Link in");
				ConnectionThread connectionThread = new ConnectionThread(connectionToClient);
				connectionThread.start();
				this.connections.add(connectionThread);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	public class ConnectionThread extends Thread{
		private Socket socket;
		private BufferedReader reader;
		private PrintWriter writer;
		private JSONReader jsonReader;
		
		public ConnectionThread(Socket connectionToClient) {
			this.socket = connectionToClient;
			try {
				this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void run() {
			while (true) {
				String jsonString = "";
				try {
					jsonString = this.reader.readLine();
					
					if (jsonString == null) {
						try {
							socket.close();
							connections.remove(this);
							break;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					jsonReader = new JSONReader(jsonString);
					String fileString = "code.cpp";
					try{
						// Create file 
						FileWriter fstream = new FileWriter("code.cpp");
						BufferedWriter out = new BufferedWriter(fstream);
						out.write(jsonReader.getCode());
						//Close the output stream
						out.close();
					}catch (Exception e){//Catch exception if any
						System.err.println("Error: " + e.getMessage());
					}
					
					RunCode runCode = new RunCode(fileString, jsonReader.getLanguage());
					runCode.run();
					
					
					System.out.println("Runtime Done");
					/*try {
						Thread.sleep(100000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server(8000);
		server.runForever();
	}

}

