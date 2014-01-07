package cocoonClient.Connector;

import JSONTransmitProtocol.newcreater.*;
import JSONTransmitProtocol.newcreater.login.CreaterLogin;
import JSONTransmitProtocol.newcreater.login.LoginCheck;
import cocoonClient.Data.UserInfo;

public class Authentication {
	public static void authenticate(String username, String password) {
        // hardcoded username and password
		JSONCreater json = new JSONCreater("login").setInfo(UserInfo.getUserInfo())
				.setLogin(new CreaterLogin("login", password, new LoginCheck(0, "")));
        UserInfo.getClient().sendMessage(json.toString());
    }
}
