package JSONTransmitProtocol.newcreater.query;

import org.json.JSONObject;

public class ResponseProblemrate extends JSONObject{
	public ResponseProblemrate(int PID, int TotalSubmission, int Accept, int WrongAnswer, int RuntimeError, int TimeLimitExceed, 
			int MemoryLimitExceed, int CompileError){
		super();
		try{
			this.put("PID", PID);
			this.put("TotalSubmission", TotalSubmission);
			this.put("Accept", Accept);
			this.put("RuntimeError", RuntimeError);
			this.put("TimeLimitExceed", TimeLimitExceed);
			this.put("MemoryLimitExceed", MemoryLimitExceed);
			this.put("CompileError", CompileError);
		}
		catch(Exception e){
			
		}
	}
}
