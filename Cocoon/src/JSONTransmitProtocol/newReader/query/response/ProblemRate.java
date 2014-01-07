package JSONTransmitProtocol.newReader.query.response;

public class ProblemRate {
	private int PID;
	private int TotalSubmission;
	private int Accept;
	private int WrongAnswer;
	private int RubtimeError;
	private int TimeLimitExceeded;
	private int MemoryLimitExceeded;
	private int UnknownError;
	public ProblemRate() {
		PID = 0;
		TotalSubmission = 0;
		Accept = 0;
		WrongAnswer = 0;
		RubtimeError = 0;
		TimeLimitExceeded = 0;
		MemoryLimitExceeded = 0;
		UnknownError = 0;
	}
	public void setAccept(int accept) {
		Accept = accept;
	}
	public void setMemoryLimitExceeded(int memoryLimitExceeded) {
		MemoryLimitExceeded = memoryLimitExceeded;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public void setRubtimeError(int rubtimeError) {
		RubtimeError = rubtimeError;
	}
	public void setTimeLimitExceeded(int timeLimitExceeded) {
		TimeLimitExceeded = timeLimitExceeded;
	}
	public void setTotalSubmission(int totalSubmission) {
		TotalSubmission = totalSubmission;
	}
	public void setUnknownError(int unknownError) {
		UnknownError = unknownError;
	}
	public void setWrongAnswer(int wrongAnswer) {
		WrongAnswer = wrongAnswer;
	}
	public int getAccept() {
		return Accept;
	}
	public int getMemoryLimitExceeded() {
		return MemoryLimitExceeded;
	}
	public int getPID() {
		return PID;
	}
	public int getRubtimeError() {
		return RubtimeError;
	}
	public int getTimeLimitExceeded() {
		return TimeLimitExceeded;
	}
	public int getTotalSubmission() {
		return TotalSubmission;
	}
	public int getUnknownError() {
		return UnknownError;
	}
	public int getWrongAnswer() {
		return WrongAnswer;
	}
}
