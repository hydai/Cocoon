package JSONTransmitProtocol.newReader.query;

import JSONTransmitProtocol.newReader.query.response.FriendList;
import JSONTransmitProtocol.newReader.query.response.ProblemRate;
import JSONTransmitProtocol.newReader.query.response.Rank;
import JSONTransmitProtocol.newReader.query.response.Statistics;

public class Response {
	private String type;
	private ProblemRate problemRate;
	private Rank rank;
	private FriendList friendList;
	private Statistics statistics;
	
	
	@SuppressWarnings("unused")
	private Response() {}
	public Response(String type) {
		this.type = type;
		problemRate = new ProblemRate();
		rank = new Rank();
		friendList = new FriendList();
		statistics = new Statistics();
	}
	public FriendList getFriendList() {
		return friendList;
	}
	public ProblemRate getProblemRate() {
		return problemRate;
	}
	public Rank getRank() {
		return rank;
	}
	public Statistics getStatistics() {
		return statistics;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
