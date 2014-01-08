package JSONTransmitProtocol.newReader.query.response;

import java.util.Map;
import java.util.TreeMap;

public class Statistics {
	private Map<String, Map<String, Integer>> statisticsMap;
	private Map<String, Integer> mixStatisticsMap;
	public Statistics() {
		mixStatisticsMap = new TreeMap<String, Integer>();
		statisticsMap = new TreeMap<String, Map<String, Integer>>();
		statisticsMap.put("Radar", new TreeMap<String, Integer>());
		statisticsMap.put("Pie", new TreeMap<String, Integer>());
	}
	public Map<String, Integer> getRadarStatisticsMap() {
		return statisticsMap.get("Radar");
	}
	public Map<String, Integer> getPieStatisticsMap() {
		return statisticsMap.get("Pie");
	}
	public Map<String, Integer> getMixStatisticsMap() {
		return mixStatisticsMap;
	}
	public void AddStatistics(String problemtype, int solved) {
		if (problemtype.equals("TotalSubmission")
				|| problemtype.equals("Accept")
				|| problemtype.equals("WrongAnswer")
				|| problemtype.equals("RuntimeError")
				|| problemtype.equals("TimeLimitExceeded")
				|| problemtype.equals("MemoryLimitExceed")
				|| problemtype.equals("CompileError")) {
			statisticsMap.get("Pie").put(problemtype, solved);
		}
		else {
			statisticsMap.get("Radar").put(problemtype, solved);
		}
		mixStatisticsMap.put(problemtype, solved);
	}
}
