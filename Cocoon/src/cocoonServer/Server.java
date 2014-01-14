package cocoonServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
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

/**
 * Server of Cocoon.
 * @author hydai
 *
 */
public class Server {
	/** store unique id in runtime */
	private RuntimeID runtimeID;
	private ServerSocket serverSocket;
	/** store all client link*/
	private List<ConnectionThread> connections = new ArrayList<ConnectionThread>();
	
	/**
	 * Create server with listening at <code>portNum</code>
	 * And get the instance of RuntimeID for assign every submission has its unique serial number.
	 * @param portNum
	 */
	public Server(int portNum) {
		try {
			this.serverSocket = new ServerSocket(portNum);
			System.out.printf("Server starts listening on port %d.\n", portNum);
			runtimeID = RuntimeID.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Listen for client to connect forever.
	 */
	public void runForever() {
		System.out.println("Server starts waiting for client.");

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
		/** parse and store the json file gived by client */
		private JSONReader jsonReader;
		
		/**
		 * 
		 * @param connectionToClient
		 */
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
			System.out.println("SendMessage> " + message);
		}
		public void run() {
			// check if the connection is disconnect of not
			while (reader != null) {
				try {
					String jsonString = "";
					jsonString = this.reader.readLine();
					
					// if jsonString is empty, the connection is over.
					if (jsonString == null)
						break;
					int runtimeIDLong = runtimeID.getRuntimeID();
					
					/** Create a JSONReader to parse the json string */
					jsonReader = new JSONReader(jsonString);
					
					/**
					 * Distribute the work to different part. 
					 * It will call ServerLogin to handle the login information, 
					 * ServerQuery to handle all query request and
					 * ServerSubmission handle the code and runtime.
					 * */ 
					if (jsonReader.getType().equals("submission")) {
						jsonReader.getSubmission().setSubmissionID(runtimeIDLong);
						ServerSubmission submission = new ServerSubmission(jsonReader);
						submission.run();
						JSONObject json = createSubmissionResult();
						System.out.println(jsonReader.getSubmission().getResult());
						broadcast(json.toString());
					}
					else if (jsonReader.getType().equals("login")) {
						ServerLogin login = new ServerLogin(jsonReader);
						login.run();
						JSONCreater json = createLoginCheck();
						sendMessage(json.toString());
					}
					else if (jsonReader.getType().equals("query")) {
						ServerQuery query = new ServerQuery(jsonReader);
						query.run();
						System.out.println(jsonReader.getQuery().getQuestion().getType());
						JSONCreater json = null;
						if (jsonReader.getQuery().getQuestion().getType().equals("problemrate")) {
							json = createResponseProblemRate();
						}
						else if (jsonReader.getQuery().getQuestion().getType().equals("friendlist")) {
							json = createResponseFriendList();
						}
						else if (jsonReader.getQuery().getQuestion().getType().equals("statistics")) {
							json = createResponseStatistics();
						}
						sendMessage(json.toString());
					}
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
			System.out.println("Disconnected");
		}
		
		/**
		 * Use own defined JSONCreater to create the response json file.
		 * @return response json file
		 */
		private JSONCreater createResponseStatistics() {
			List<JSONObject> list = new ArrayList<JSONObject>();
			for(Iterator<Entry<String, Integer>> iterator = 
					jsonReader.getQuery().getResponse().getStatistics().
					getStatisticsMap().entrySet().iterator();
					iterator.hasNext();) {
				JSONObject tmp = new JSONObject();
				Entry<String, Integer> tmpEntry = iterator.next();
				try {
					tmp.put(tmpEntry.getKey(), tmpEntry.getValue());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				list.add(tmp);
			}
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
									"statistics",
									new JSONArray(list))));
			return json;
		}
		
		/**
		 * Use own defined JSONCreater to create the response json file.
		 * @return response json file
		 */
		private JSONCreater createResponseFriendList() {
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
									"friendlist",
									new JSONArray(jsonReader.getQuery().getResponse().getFriendList().getFriendlist()))));
			return json;
		}
		
		/**
		 * Use own defined JSONCreater to create the response json file.
		 * @return response json file
		 */
		private JSONCreater createSubmissionResult() {
			JSONCreater json = new JSONCreater("submission").
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
			return json;
		}
		
		/**
		 * Use own defined JSONCreater to create the response json file.
		 * @return response json file
		 */
		private JSONCreater createLoginCheck() {
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
			return json;
		}
		
		/**
		 * Use own defined JSONCreater to create the response json file.
		 * @return response json file
		 */
		private JSONCreater createResponseProblemRate() {
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
			return json;
		}
	}
	
	/**
	 * Send <code>message</code> to all clients.
	 * @param message
	 */
	private void broadcast(String message) {
		for (ConnectionThread connection: connections) {
			connection.sendMessage(message);
		}
	}
	
	/**
	 * Server entry point.
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = new Server(8000);
		server.runForever();
	}

}

