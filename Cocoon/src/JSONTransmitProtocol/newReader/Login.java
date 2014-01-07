package JSONTransmitProtocol.newReader;

public class Login {
	private String type;
	private String password;
	private int UID;
	private String statement;
	
	@SuppressWarnings("unused")
	private Login() {}
	
	public Login(String type) {
		this.type = type;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUID(int uID) {
		UID = uID;
	}
	public String getPassword() {
		return password;
	}
	public String getStatement() {
		return statement;
	}
	public String getType() {
		return type;
	}
	public int getUID() {
		return UID;
	}
}
