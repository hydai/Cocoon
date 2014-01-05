package cocoonClient.Connector;

public class Authentication {
	public static boolean authenticate(String username, String password) {
        // hardcoded username and password
        if (username.equals("henry") && password.equals("henry")) {
            return true;
        }
        return false;
    }
}
