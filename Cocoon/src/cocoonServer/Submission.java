package cocoonServer;

import java.io.BufferedWriter;
import java.io.FileWriter;

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
			FileWriter fstream = new FileWriter("/runtime/"+fileString);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(jsonReader.getSubmission().getCode());
			//Close the output stream
			out.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
		runCode = new RunCode(jsonReader.getSubmission().getLanguage(), jsonReader.getSubmission().getSubmissionID());
	}
	public void run() {
		runCode.run();
		result = runCode.getResult();
	}
	public String getResult() {
		return result;
	}
}
