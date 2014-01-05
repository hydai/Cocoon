package cocoonClient.Connector;

import JSONTransmitProtocol.creater.JSONCreater;
import JSONTransmitProtocol.reader.JSONReader;
import cocoonClient.Data.UserInfo;

public class Authentication {
	public static void authenticate(String username, String password) {
        // hardcoded username and password
		JSONCreater json = new JSONCreater().setType("login").setLogin("login", username, password);
        UserInfo.getClient().sendMessage(json.toString());
    }
}
