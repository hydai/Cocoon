package JSONTransmitProtocol.newReader.query.response;

import java.util.ArrayList;
import java.util.List;


public class FriendList {
	private List<String> friendlist;
	public FriendList() {
		friendlist = new ArrayList<String>();
	}
	public List<String> getFriendlist() {
		return friendlist;
	}
	public void addFriend(String username) {
		friendlist.add(username);
	}
}
