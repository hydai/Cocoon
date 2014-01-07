package JSONTransmitProtocol.newcreater.login;

import org.json.JSONObject;

public class LoginCheck extends JSONObject{
	public LoginCheck(int UID, String statement){
		super();
		try{
			this.put("UID", UID);
			this.put("statement", statement);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
