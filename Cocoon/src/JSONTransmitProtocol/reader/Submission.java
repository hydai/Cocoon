package JSONTransmitProtocol.reader;

public class Submission {
	private String language;
	private String code;
	private long submissionID;
	private Info info;
	
	@SuppressWarnings("unused")
	private Submission() {
		language = "";
		code = "";
		submissionID = 0;
	}
	
	public Submission(String languageString, String codeString, Info info) {
		if (languageString.equals("C")) {
			language = "C";
		}
		else {
			language = "CPP";
		}
		code = codeString;
		this.info = info;
	}
	
	public void setSubmissionID(long submissionID) {
		this.submissionID = submissionID;
	}
	public String getCode() {
		return code;
	}
	public String getLanguage() {
		return language;
	}
	public long getSubmissionID() {
		return submissionID;
	}
	public Info getInfo() {
		return info;
	}
}
