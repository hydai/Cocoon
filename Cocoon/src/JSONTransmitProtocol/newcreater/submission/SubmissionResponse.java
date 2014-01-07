package JSONTransmitProtocol.newcreater.submission;

import org.json.JSONObject;

public class  SubmissionResponse extends JSONObject{
	public  SubmissionResponse(int UID, int PID, String username, int submissionID, String result, String time){
		super();
		try{
			this.put("UID", UID);
			this.put("PID", PID);
			this.	put("username", username);
			this.put("result", result);
			this.put("time", time);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}