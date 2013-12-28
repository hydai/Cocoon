package JSONTransmitProtocol.reader;

public class Broadcast {
	private String type;
	private Status status;
	
	@SuppressWarnings("unused")
	private Broadcast() {
		type = "";
	}
	public Broadcast(String type, Status status) {
		this.type = type;
		this.status = status;
	}
	public Status getStatus() {
		return status;
	}
	public String getType() {
		return type;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void setType(String type) {
		this.type = type;
	}
}
