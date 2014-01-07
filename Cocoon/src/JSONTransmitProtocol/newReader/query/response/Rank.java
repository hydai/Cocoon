package JSONTransmitProtocol.newReader.query.response;

import java.util.Map;
import java.util.TreeMap;

public class Rank {
	private Map<String, Integer> rankMap;
	public Rank() {
		rankMap = new TreeMap<String, Integer>();
	}
	public Map<String, Integer> getRankMap() {
		return rankMap;
	}
	public void AddRank(String username, int solved) {
		rankMap.put(username, solved);
	}
}
