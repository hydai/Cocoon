package cocoonServer;

import java.io.PrintWriter;

import JSONTransmitProtocol.newReader.JSONReader;
import cocoonServer.mysql.SQLUserData;

public class ServerSubmission {
	private JSONReader jsonReader;
	private RunCode runCode;
	private String result;
	@SuppressWarnings("unused")
	private ServerSubmission() {
		// Do nothing
	}
	public ServerSubmission(JSONReader reader) {
		this.jsonReader = reader;

		String type;
		if (jsonReader.getSubmission().getLanguage().equals("C")) {
			type = ".c";
		}
		else {
			type = ".cpp";
		}
		String fileString = (jsonReader.getSubmission().getSubmissionID()+type);
		try{
			// Create file 
			fileString = "/home/cocoon/git/Cocoon/Cocoon/runtime/"+fileString;
			PrintWriter printWriter = new PrintWriter(fileString, "UTF-8");
			printWriter.print(jsonReader.getSubmission().getCode());
			printWriter.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
		runCode = new RunCode(
				jsonReader.getSubmission().getLanguage(), 
				jsonReader.getSubmission().getSubmissionID());
		runCode.setMemoryLimit(130000);
		runCode.setTimeLimit(1000);
		runCode.setProblemID(jsonReader.getInfo().getPID());
		runCode.setStrictJudge(true);
	}
	public void run() {
		runCode.run();
		result = runCode.getResult();
		jsonReader.getSubmission().setResult(result);
	}
	public String getResult() {
		return result;
	}
	public int getUserID() {
		return jsonReader.getInfo().getUID();
	}
	public int getSubmissionID() {
		return jsonReader.getSubmission().getSubmissionID();
	}
	public String getTime() {
		return jsonReader.getInfo().getTime();
	}
	public int getPID() {
		return jsonReader.getInfo().getPID();
	}
	public String getUsername() {
		SQLUserData userData = new SQLUserData();
		return userData.getUsername(jsonReader.getInfo().getUID());
	}
}
