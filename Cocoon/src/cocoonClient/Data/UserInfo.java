package cocoonClient.Data;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import JSONTransmitProtocol.newcreater.info.CreaterInfo;
import cocoonClient.Connector.AbstractConnector;
import cocoonClient.Connector.ChatClient;
import cocoonClient.Frame.MainFrame;

public class UserInfo {
	private static ChatClient client;
	private static UserInfo userinfo;
	private static int uid;
	private static int pid;
	private static MainFrame parent;
	private static String ip = "", problemName = "", username = "";
	private static HashMap<String, AbstractConnector> panels = new HashMap<String, AbstractConnector>();
	private UserInfo(MainFrame parent, int uid){
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
	public static void initUserInfo(MainFrame parent,int uid){
		userinfo = new UserInfo(parent, uid);
	}
	
	public static ChatClient getClient(){
		return client;
	}
	public static void setProblemName(String name){
		UserInfo.problemName = name;
	}
	public static String getProblemName(){
		return UserInfo.problemName;
	}
	public static UserInfo getInstance(){
		return userinfo;
	}
	
	public static MainFrame getMainFrame(){
		return parent;
	}
	
	public static int getUID(){
		return uid;
	}
	public static void setUID(int uid){
		UserInfo.uid = uid;
	}
	public static void setPID(int pid){
		UserInfo.pid = pid;
	}
	
	public static int getPID(){
		return pid;
	}
	
	public static String getIP(){
		return ip;
	}
	
	public static HashMap<String, AbstractConnector> getPanels(){
		return UserInfo.panels;
	}
	public static String getUsername(){
		return UserInfo.username;
	}
	public static void  setUsername(String username){
		UserInfo.username = username;
	}
	public static CreaterInfo getUserInfo(){
		String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		return new CreaterInfo(username, pid, uid, ip, time);
	}
}
