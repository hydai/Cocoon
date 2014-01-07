package JSONTransmitProtocol.newcreater.submission;

import org.json.JSONObject;

import JSONTransmitProtocol.reader.Submission;

public class CreaterSubmission extends JSONObject{
	public CreaterSubmission(String type, SubmissionSent sent) {
		super();
		try{
			this.put("type", type);
			this.put("sent", sent);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public CreaterSubmission(String type, SubmissionResponse response) {
		super();
		try{
			this.put("type", type);
			this.put("response", response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

