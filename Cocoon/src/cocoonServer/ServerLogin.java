package cocoonServer;


import JSONTransmitProtocol.newReader.JSONReader;
import cocoonServer.mysql.SQLUserData;

public class ServerLogin {
	private JSONReader jsonReader;
	private SQLUserData userData;
	
	@SuppressWarnings("unused")
	private ServerLogin() {	}
	
	public ServerLogin(JSONReader jsonReader) {
		this.jsonReader = jsonReader;
		userData = new SQLUserData();
		
	}
	
	public void register() {
		if (!userData.isUserExist(jsonReader.getInfo().getUsername())) {
			userData.insertTable(jsonReader.getInfo().getUsername(), jsonReader.getLogin().getPassword());
			jsonReader.getLogin().setUID(userData.getUserId(jsonReader.getInfo().getUsername()));
			jsonReader.getLogin().setStatement("");
		}
		else {
			jsonReader.getLogin().setUID(-1);
			jsonReader.getLogin().setStatement("Used username");
		}
	}
	public void login() {
		jsonReader.getLogin().setUID(-1);
		if (userData.isUserExist(jsonReader.getInfo().getUsername())) {
			jsonReader.getLogin().setUID(
					userData.checkUserPassword(
							jsonReader.getInfo().getUsername(),
							jsonReader.getLogin().getPassword()));
			if (jsonReader.getLogin().getUID() != -1) {
				jsonReader.getLogin().setStatement("");
			}
			else {
				jsonReader.getLogin().setUID(-1);
				jsonReader.getLogin().setStatement("incorrect username or password");
			}
		}
	}
	public long getUid() {
		return jsonReader.getLogin().getUID();
	}
	public String getUsername() {
		return jsonReader.getInfo().getUsername();
	}
	public String getPassword() {
		return jsonReader.getLogin().getPassword();
	}
	public String getStatement() {
		return jsonReader.getLogin().getStatement();
	}
	public void setStatement(String statement) {
		jsonReader.getLogin().setStatement(statement);;
	}
	public String getType() {
		return jsonReader.getLogin().getType();
	}
	public void run() {
		if (jsonReader.getLogin().getType().equals("login")) {
			this.login();
		}
		else if (jsonReader.getLogin().getType().equals("register")){
			this.register();
		}
	}
}
