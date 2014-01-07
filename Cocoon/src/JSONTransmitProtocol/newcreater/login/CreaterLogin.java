package JSONTransmitProtocol.newcreater.login;


import org.json.JSONObject;

public class CreaterLogin extends JSONObject{
	public CreaterLogin(String type, String password, LoginCheck check){
		super();
		try{
			this.put("type", type);
			this.put("password", password);
			this.put("check", check);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
