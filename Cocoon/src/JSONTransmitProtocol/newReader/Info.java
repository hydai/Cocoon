package JSONTransmitProtocol.newReader;

public class Info {
	private String username;
	private int PID;
	private int UID;
	private String IP;
	private String time;
	@SuppressWarnings("unused")
	private Info() {}
	
	public Info(int UID) {
		this.UID = UID;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public void setPID(int pID) {
		PID = pID;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setUID(int uID) {
		UID = uID;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIP() {
		return IP;
	}
	public int getPID() {
		return PID;
	}
	public String getTime() {
		return time;
	}
	public int getUID() {
		return UID;
	}
	public String getUsername() {
		return username;
	}
}
