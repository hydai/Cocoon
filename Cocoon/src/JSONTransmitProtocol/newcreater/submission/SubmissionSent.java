package JSONTransmitProtocol.newcreater.submission;

import org.json.JSONObject;

public class  SubmissionSent extends JSONObject{
	public  SubmissionSent(String code, String language){
		super();
		try{
			this.put("code", code);
			this.put("language", language);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}