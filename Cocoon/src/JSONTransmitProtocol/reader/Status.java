package JSONTransmitProtocol.reader;

public class Status {
	private Long UID;
	private Long submissionID;
	private String result;
	private String time;
	
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
}
