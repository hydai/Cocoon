package cocoonClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class UserInfo {
	private static ChatClient client;
	private static UserInfo userinfo;
	private static long uid;
	private static MainFrame parent;
	private static String ip;
	private UserInfo(MainFrame parent, long uid){
		UserInfo.parent = parent;
		UserInfo.uid = uid;
		UserInfo.client = new ChatClient(parent, "127.0.0.1", 8000);
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public static void initUserInfo(MainFrame parent, long uid){
		userinfo = new UserInfo(parent, uid);
	}
	
	public static ChatClient getClient(){
		return client;
	}
	
	public static UserInfo getInstance(){
		return userinfo;
	}
	
	public static MainFrame getMainFrame(){
		return parent;
	}
	
	public static long getUID(){
		return uid;
	}
	
	public static String getIP(){
		return ip;
	}
}
