package JSONTransmitProtocol.reader;

public class Status {
	private long UID;
	private long submissionID;
	private String result;
	private String time;
	private String username;
	private int PID;
	
	@SuppressWarnings("unused")
	private Status() {
		UID = 0L;
		submissionID = 0L;
		result = "";
		time = "";
	}
	
	public Status(Long UID, Long submissionID, String result, String time) {
		this.UID = UID;
		this.submissionID = submissionID;
		this.result = result;
		this.time = time;
	}
	
	public String getResult() {
		return result;
	}
	public Long getSubmissionID() {
		return submissionID;
	}
	public String getTime() {
		return time;
	}
	public Long getUID() {
		return UID;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPID() {
		return PID;
	}
	public String getUsername() {
		return username;
	}
}
