package JSONTransmitProtocol.reader;

public class Info {
	private long UID;
	private String IP;
	private String time;
	
	@SuppressWarnings("unused")
	private Info() {
		UID = 0;
		IP = "";
		time = "";
	}
	
	public Info(long UID, String IP, String timeString) {
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
