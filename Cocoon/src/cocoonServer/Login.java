package cocoonServer;

import JSONTransmitProtocol.reader.JSONReader;
import cocoonServer.mysql.SQLUserData;

public class Login {
	private JSONReader jsonReader;
	private SQLUserData userData;
	
	@SuppressWarnings("unused")
	private Login() {	}
	
	public Login(JSONReader jsonReader) {
		this.jsonReader = jsonReader;
		userData = new SQLUserData();
		
	}
	
	public void register() {
		if (!userData.isUserExist(jsonReader.getLogin().getUsername())) {
			userData.insertTable(jsonReader.getLogin().getUsername(), jsonReader.getLogin().getPassword());
			jsonReader.getLogin().setUid(userData.getUserId(jsonReader.getLogin().getUsername()));
			jsonReader.getLogin().setStatement("");
		}
		else {
			jsonReader.getLogin().setUid(-1);
			jsonReader.getLogin().setStatement("Used username");
		}
	}
	public void login() {
		jsonReader.getLogin().setUid(-1);
		if (userData.isUserExist(jsonReader.getLogin().getUsername())) {
			jsonReader.getLogin().setUid(
					userData.checkUserPassword(
							jsonReader.getLogin().getUsername(),
							jsonReader.getLogin().getPassword()));
			if (jsonReader.getLogin().getUid() != -1) {
				jsonReader.getLogin().setStatement("");
			}
			else {
				jsonReader.getLogin().setUid(-1);
				jsonReader.getLogin().setStatement("incorrect username or password");
			}
		}
	}
	public long getUid() {
		return jsonReader.getLogin().getUid();
	}
	public String getUsername() {
		return jsonReader.getLogin().getUsername();
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
