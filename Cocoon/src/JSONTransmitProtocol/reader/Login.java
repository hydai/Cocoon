package JSONTransmitProtocol.reader;

public class Login {
	private String type;
	private String username;
	private String password;
	private long uid;
	private String statement;
	@SuppressWarnings("unused")
	private Login() {}
	
	public Login(String type, String username, String password) {
		this.type = type;
		this.username = username;
		this.password = password;
		uid = -1;
		this.statement = "";
	}
	
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getType() {
		return type;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public long getUid() {
		return uid;
	}
	public String getStatement() {
		return statement;
	}
}
