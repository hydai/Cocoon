package JSONTransmitProtocol.reader;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONReader{
	/** transform json file to String */
	private String jsonString;
	private String type;
	private Submission submission;
	private Query query;
	private Broadcast broadcast;
	private Status status;
	private Login login;
	public JSONReader(String jsonString) {
		this.jsonString = jsonString;
		System.out.println(this.jsonString);
		init();
	}
	
	/**
	 * Parse the json file
	 */
	private void init() {
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			type = jsonObject.getString("type");
			if (type.equals("submission")) {
				JSONObject JSONsubmission = jsonObject.getJSONObject("submission");
				JSONObject JSONinfo = JSONsubmission.getJSONObject("info");
				Info info = new Info(JSONinfo.getInt("PID"), JSONinfo.getLong("UID"), JSONinfo.getString("IP"), JSONinfo.getString("time"));
				submission = new Submission(JSONsubmission.getString("language"), JSONsubmission.getString("code"), info);
			}
			else if (type.equals("login")) {
				JSONObject JSONlogin = jsonObject.getJSONObject("login");
				String type = JSONlogin.getString("type");
				login = new Login(type, JSONlogin.getString("username"), JSONlogin.getString("password"));
				if (type.equals("check")) {
					login.setUid(JSONlogin.getJSONObject("check").getLong("UID"));
					login.setStatement(JSONlogin.getJSONObject("check").getString("statement"));
				}
			}
			else if (type.equals("query")) {
				JSONObject JSONquery = jsonObject.getJSONObject("query");
				query = new Query(JSONquery.getString("type"));
			}
			else if (type.equals("broadcast")) {
				JSONObject JSONbroadcast = jsonObject.getJSONObject("broadcast");
				JSONObject JSONstatus = JSONbroadcast.getJSONObject("status");
				status = new Status(JSONstatus.getLong("UID"), JSONstatus.getLong("submissionID"), JSONstatus.getString("result"), JSONstatus.getString("time"));
				status.setPID(JSONstatus.getInt("PID"));
				status.setUsername(JSONstatus.getString("username"));
				broadcast = new Broadcast(JSONbroadcast.getString("type"), status);
			}
			else {
				System.out.println("Unknown Error!");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getType() {
		return type;
	}
	public Submission getSubmission() {
		return submission;
	}
	public Query getQuery() {
		return query;
	}
	public Broadcast getBroadcast() {
		return broadcast;
	}
	public Status getStatus() {
		return status;
	}
	public Login getLogin() {
		return login;
	}
}
