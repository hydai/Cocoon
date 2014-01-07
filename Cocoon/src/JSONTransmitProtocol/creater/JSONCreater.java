package JSONTransmitProtocol.creater;
import org.json.*;


public class JSONCreater extends JSONObject{
	private JSONObject submission = new JSONObject();
	private JSONObject query = new JSONObject();
	private JSONObject login = new JSONObject();
	private JSONObject broadcast = new JSONObject();
	
	public JSONCreater(JSONCreater json){
		try {
			submission = json.getJSONObject("submission");
			query = json.getJSONObject("query");
			login = json.getJSONObject("login");
			broadcast = json.getJSONObject("broadcast");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public JSONCreater(){
		super();
		try{
			this.put("type", "");
			
			this.put("submission", submission);
			submission.put("info", new JSONObject());
			
			this.put("query", query);
			
			this.put("login", login);
			login.put("check", new JSONObject());
			
			this.put("broadcast", broadcast);
			broadcast.put("status", new JSONObject());
			broadcast.put("system", new JSONObject());
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	/*
	 * Type
	 */
	
	public JSONCreater setType(String type){
		try {
			this.put("type", type);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/*
	 * Query
	 */
	
	public JSONCreater setQuery(String query){
		try {
			this.put("type", query);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}
	/*
	 * Broadcast
	 */
	public JSONCreater setBroadcast(String type, long uid){
		try {
			broadcast.put("type", type);
			broadcast.put("UID", uid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}
	public JSONCreater setBroadcastStatus(long uid, int pid, String username, long submissionID, String result, String time){
		try {
			JSONObject status = broadcast.getJSONObject("status");
			status.put("UID", uid);
			status.put("PID", pid);
			status.put("username", username);
			status.put("submissionID", submissionID);
			status.put("result", result);
			status.put("time", time);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}
	public JSONCreater setBroadcastStatus(long uid, long submissionID, String result, String time){
		try {
			JSONObject status = broadcast.getJSONObject("status");
			status.put("UID", uid);
			status.put("submissionID", submissionID);
			status.put("result", result);
			status.put("time", time);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}
	public JSONCreater setBroadcastSystem(String message){
		try {
			JSONObject system = broadcast.getJSONObject("system");
			system.put("messsage", message);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/*
	 * Submission
	 */
	public JSONCreater setSubmission(String code, String language){
		try {
			submission.put("code", code);
			submission.put("language", language);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}
	public JSONCreater setSubmissionInfo(int pid, long uid, String ip, String time){
		try {
			JSONObject info = submission.getJSONObject("info");
			info.put("PID", pid);
			info.put("UID", uid);
			info.put("IP", ip);
			info.put("time", time);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/*
	 * Login
	 */
	public JSONCreater setLogin(String type, String username, String password){
		try {
			login.put("type", type);
			login.put("username", username);
			login.put("password", password);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}
	public JSONCreater setLoginCheck(Long UID, String statement){
		try {
			JSONObject check = login.getJSONObject("check");
			check.put("UID", UID);
			check.put("statement", statement);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}
}
