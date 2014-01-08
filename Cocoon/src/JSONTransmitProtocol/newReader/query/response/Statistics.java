package JSONTransmitProtocol.newReader.query.response;

import java.util.Map;
import java.util.TreeMap;

public class Statistics {
	private Map<String, Integer> statisticsMap;
	public Statistics() {
		statisticsMap = new TreeMap<String, Integer>();
	}
	public void AddStatistics(String problemtype, int solved) {
		statisticsMap.put(problemtype, solved);
	}
	public Map<String, Integer> getStatisticsMap() {
		return statisticsMap;
	}
}
