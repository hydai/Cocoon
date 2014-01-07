package JSONTransmitProtocol.newReader;

public class Submission {
	private String type;
	private String language;
	private String code;
	private int UID;
	private int PID;
	private String username;
	private int submissionID;
	private String result;
	private String time;
	
	@SuppressWarnings("unused")
	private Submission() {}
	public Submission(String type) {
		this.type = type;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setSubmissionID(int submissionID) {
		this.submissionID = submissionID;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUID(int uID) {
		UID = uID;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCode() {
		return code;
	}
	public String getLanguage() {
		return language;
	}
	public int getPID() {
		return PID;
	}
	public String getResult() {
		return result;
	}
	public int getSubmissionID() {
		return submissionID;
	}
	public String getTime() {
		return time;
	}
	public String getType() {
		return type;
	}
	public int getUID() {
		return UID;
	}
	public String getUsername() {
		return username;
	}
}
