package JSONTransmitProtocol.reader;

public class Info {
	private long UID;
	private String IP;
	private String time;
	private int PID;
	@SuppressWarnings("unused")
	private Info() {
		UID = 0;
		IP = "";
		time = "";
	}
	
	public Info(int PID, long UID, String IP, String timeString) {
		this.PID = PID;
		this.UID = UID;
		this.IP = IP;
		this.time = timeString;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public void setTimeString(String timeString) {
		this.time = timeString;
	}
	public void setUID(long uID) {
		UID = uID;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public int getPID(){
		return PID;
	}
	public String getIP() {
		return IP;
	}
	public String getTime() {
		return time;
	}
	public long getUID() {
		return UID;
	}
}
