package cocoonClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



public class ChatServer {

	private ServerSocket serverSocket;
	private List<ConnectionThread> connections = new ArrayList<ConnectionThread>();
	
	public ChatServer(int portNum) {
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
		while(true){
			try {
				Socket socket = this.serverSocket.accept();
				ConnectionThread ct = new ConnectionThread(socket);
				System.out.println(socket);
				ct.start();
				connections.add(ct);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void broadcast(String message) {
		for (ConnectionThread connection: connections) {
			connection.sendMessage(message);
		}
	}
	
	// Define an inner class (class name should be ConnectionThread)
	public class ConnectionThread extends Thread{
		private Socket socket;
		private BufferedReader reader;
		private PrintWriter writer;
		public ConnectionThread(Socket socket){
			this.socket = socket;
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void sendMessage(String msg){
			if(writer != null){
				this.writer.print(msg + "\n");
				this.writer.flush();
			}
		}
		@Override
		public void run(){
			while(reader != null){	
				try {
					String line;
					line = this.reader.readLine();
					if(line == null)
						break;
					System.out.println(line);
				} catch (IOException e) {
					break;
				}
			}
			System.out.println("Disconnected");
		}
	}

	
	public static void main(String[] args) {
		ChatServer server = new ChatServer(8000);
		server.runForever();
	}
	
}
