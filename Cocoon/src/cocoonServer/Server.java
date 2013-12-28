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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import org.json.JSONObject;

import cocoonClient.ChatServer.ConnectionThread;
import JSONTransmitProtocol.JSONCreater;
import JSONTransmitProtocol.reader.JSONReader;

public class Server {
	private RuntimeID runtimeID;
	private ServerSocket serverSocket;
	private List<ConnectionThread> connections = new ArrayList<ConnectionThread>();
	private Queue<Submission> submissionQueue = new LinkedList<Submission>();
	public Server(int portNum) {
		try {
			this.serverSocket = new ServerSocket(portNum);
			System.out.printf("Server starts listening on port %d.\n", portNum);
			runtimeID = RuntimeID.getInstance();
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
	
	public class JudgeQueueThread extends Thread{
		public JudgeQueueThread() {
		}
		@Override
		public void run() {
			while (true) {
				while (!submissionQueue.isEmpty()) {
					Submission submission = submissionQueue.poll();
					submission.run();
					broadcast(submission.getResult());
				}
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
		public void sendMessage(String message) {
			this.writer.println(message);
			this.writer.flush();
		}
		public void run() {
			while (reader != null) {
				try {
					String jsonString = "";
					jsonString = this.reader.readLine();
					if (jsonString == null)
						break;
					Long runtimeIDLong = runtimeID.getRuntimeID();
					jsonReader = new JSONReader(jsonString);
					
					if (jsonReader.getType().equals("submission")) {
						jsonReader.getSubmission().setSubmissionID(runtimeIDLong);
						Submission submission = new Submission(jsonReader);
						submissionQueue.offer(submission);
					}
					
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
			System.out.println("Disconnected");
		}
	}
	private void broadcast(String message) {
		for (ConnectionThread connection: connections) {
			connection.sendMessage(message);
		}
	}
	public static void main(String[] args) {
		Server server = new Server(8000);
		server.runForever();
	}

}

