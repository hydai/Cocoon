package cocoonServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import JSONTransmitProtocol.newReader.JSONReader;
import JSONTransmitProtocol.newcreater.JSONCreater;
import JSONTransmitProtocol.newcreater.info.CreaterInfo;
import JSONTransmitProtocol.newcreater.login.CreaterLogin;
import JSONTransmitProtocol.newcreater.login.LoginCheck;
import JSONTransmitProtocol.newcreater.query.CreaterQuery;
import JSONTransmitProtocol.newcreater.query.QueryResponse;
import JSONTransmitProtocol.newcreater.query.ResponseProblemrate;
import JSONTransmitProtocol.newcreater.submission.CreaterSubmission;
import JSONTransmitProtocol.newcreater.submission.SubmissionResponse;


public class Server {
	private RuntimeID runtimeID;
	private ServerSocket serverSocket;
	private List<ConnectionThread> connections = new ArrayList<ConnectionThread>();
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
					int runtimeIDLong = runtimeID.getRuntimeID();
					jsonReader = new JSONReader(jsonString);
					if (jsonReader.getType().equals("submission")) {
						jsonReader.getSubmission().setSubmissionID(runtimeIDLong);
						ServerSubmission submission = new ServerSubmission(jsonReader);
						submission.run();
						JSONObject json = new JSONCreater("submission").
								setInfo(new CreaterInfo(
										jsonReader.getInfo().getUsername(),
										jsonReader.getInfo().getPID(), 
										jsonReader.getInfo().getUID(),
										jsonReader.getInfo().getIP(),
										jsonReader.getInfo().getTime())).
								setSubmission(new CreaterSubmission(
										"response", new SubmissionResponse(
												jsonReader.getInfo().getUID(), 
												jsonReader.getInfo().getPID(), 
												jsonReader.getInfo().getUsername(), 
												jsonReader.getSubmission().getSubmissionID(),
												jsonReader.getSubmission().getResult(),
												jsonReader.getInfo().getTime())));
						System.out.println(jsonReader.getSubmission().getResult());
						broadcast(json.toString());
					}
					else if (jsonReader.getType().equals("login")) {
						ServerLogin login = new ServerLogin(jsonReader);
						login.run();
						JSONCreater json = new JSONCreater("login").
								setInfo(new CreaterInfo(
										jsonReader.getInfo().getUsername(),
										jsonReader.getInfo().getPID(), 
										jsonReader.getInfo().getUID(),
										jsonReader.getInfo().getIP(),
										jsonReader.getInfo().getTime())).
								setLogin(new CreaterLogin(
										"check", 
										jsonReader.getLogin().getPassword(),
										new LoginCheck(
												jsonReader.getLogin().getUID(),
												jsonReader.getLogin().getStatement())));
						sendMessage(json.toString());
					}
					else if (jsonReader.getType().equals("query")) {
						ServerQuery query = new ServerQuery(jsonReader);
						query.run();
						JSONCreater json = new JSONCreater("query").
								setInfo(new CreaterInfo(
										jsonReader.getInfo().getUsername(),
										jsonReader.getInfo().getPID(), 
										jsonReader.getInfo().getUID(),
										jsonReader.getInfo().getIP(),
										jsonReader.getInfo().getTime())).
								setQuery(new CreaterQuery(
										"response", 
										new QueryResponse(
												"problemrate", 
												new ResponseProblemrate(
														jsonReader.getQuery().getResponse().getProblemRate().getPID(), 
														jsonReader.getQuery().getResponse().getProblemRate().getTotalSubmission(),
														jsonReader.getQuery().getResponse().getProblemRate().getAccept(), 
														jsonReader.getQuery().getResponse().getProblemRate().getWrongAnswer(), 
														jsonReader.getQuery().getResponse().getProblemRate().getRubtimeError(), 
														jsonReader.getQuery().getResponse().getProblemRate().getTimeLimitExceeded(), 
														jsonReader.getQuery().getResponse().getProblemRate().getMemoryLimitExceeded(), 
														jsonReader.getQuery().getResponse().getProblemRate().getCompileError()))));
						sendMessage(json.toString());
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

