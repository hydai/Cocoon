package JSONTransmitProtocol.newcreater.info;


import org.json.JSONObject;

public class CreaterInfo extends JSONObject{
	CreaterInfo(String username, int PID, int UID, String IP, String time){
		super();
		try{
			this.put("username", username);
			this.put("PID", PID);
			this.put("UID", UID);
			this.put("IP", IP);
			this.put("time", time);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
