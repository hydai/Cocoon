package cocoonServer;

import java.io.PrintWriter;

import JSONTransmitProtocol.reader.JSONReader;

public class Submission {
	private JSONReader jsonReader;
	private RunCode runCode;
	private String result;
	@SuppressWarnings("unused")
	private Submission() {
		// Do nothing
	}
	public Submission(JSONReader reader) {
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
		
		runCode = new RunCode(jsonReader.getSubmission().getLanguage(), jsonReader.getSubmission().getSubmissionID());
		runCode.setMemoryLimit(130000);
		runCode.setTimeLimit(1000);
		runCode.setProblemID(2);
		runCode.setStrictJudge(true);
	}
	public void run() {
		runCode.run();
		result = runCode.getResult();
	}
	public String getResult() {
		return result;
	}
	public Long getUserID() {
		return jsonReader.getSubmission().getInfo().getUID();
	}
	public Long getSubmissionID() {
		return jsonReader.getSubmission().getSubmissionID();
	}
	public String getTime() {
		return jsonReader.getSubmission().getInfo().getTime();
	}
}
