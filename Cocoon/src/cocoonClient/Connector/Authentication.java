package cocoonClient.Connector;

import JSONTransmitProtocol.creater.JSONCreater;
import JSONTransmitProtocol.reader.JSONReader;
import cocoonClient.Data.UserInfo;

public class Authentication {
	public static boolean authenticate(String username, String password) {
        // hardcoded username and password
		if(username.equals("cocoon") && password.equals("admin"))
			return true;
		JSONCreater json = new JSONCreater().setType("login").setLogin("login", username, password);
        UserInfo.getClient().sendMessage(json.toString());
		String response = UserInfo.getClient().getResponse();
		System.out.println(response);
		JSONReader reader = new JSONReader(response);
		if(reader.getType().equals("login")){
			System.out.println(reader.getLogin().getUid());
			if(reader.getLogin().getUid() > 0L){
				
				return true;
			}
		}
		return false;
    }
}
