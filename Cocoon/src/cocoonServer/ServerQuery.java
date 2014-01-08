package cocoonServer;

import cocoonServer.mysql.SQLFriendData;
import cocoonServer.mysql.SQLProblemRate;
import cocoonServer.mysql.SQLPieStatisticsData;
import cocoonServer.mysql.SQLRadarStatisticsData;
import cocoonServer.mysql.SQLUserData;
import JSONTransmitProtocol.newReader.JSONReader;

public class ServerQuery {
	private JSONReader jsonReader;
	private SQLProblemRate sqlProblemRate;
	private SQLFriendData sqlFriendData;
	private SQLPieStatisticsData sqlPieStatisticsData;
	private SQLRadarStatisticsData sqlRadarStatisticsData;
	@SuppressWarnings("unused")
	private ServerQuery() {
		// Do nothing
	}
	public ServerQuery(JSONReader reader) {
		this.jsonReader = reader;
		sqlPieStatisticsData = new SQLPieStatisticsData();
		sqlRadarStatisticsData = new SQLRadarStatisticsData();
		sqlProblemRate = new SQLProblemRate();
		sqlFriendData = new SQLFriendData();
	}
	public void run() {
		if (jsonReader.getQuery().getQuestion().getType().equals("problemrate")) {
			runProblemRateQuery();
		}
		else if (jsonReader.getQuery().getQuestion().getType().equals("friendlist")) {
			runFriendListQuery();
		}
		else if (jsonReader.getQuery().getQuestion().getType().equals("statistics")) {
			runStatisticsQuery();
		}
	}
	private void runStatisticsQuery() {
		SQLUserData userData = new SQLUserData();
		int searchID = userData.getUserId(jsonReader.getQuery().getQuestion().getStatistics());
		sqlPieStatisticsData.runProblemRateQuery(searchID);
		
		sqlRadarStatisticsData.runRadarStatisticsQuery(searchID);
		jsonReader.getQuery().getResponse().getStatistics().AddStatistics("TotalSubmission", sqlPieStatisticsData.getTotalSubmission());
		jsonReader.getQuery().getResponse().getStatistics().AddStatistics("Accept", sqlPieStatisticsData.getAccept());
		jsonReader.getQuery().getResponse().getStatistics().AddStatistics("WrongAnswer", sqlPieStatisticsData.getWrongAnswer());
		jsonReader.getQuery().getResponse().getStatistics().AddStatistics("RuntimeError", sqlPieStatisticsData.getRuntimeError());
		jsonReader.getQuery().getResponse().getStatistics().AddStatistics("TimeLimitExceeded", sqlPieStatisticsData.getTimeLimitExceeded());
		jsonReader.getQuery().getResponse().getStatistics().AddStatistics("MemoryLimitExceeded", sqlPieStatisticsData.getMemoryLimitExceeded());
		jsonReader.getQuery().getResponse().getStatistics().AddStatistics("CompileError", sqlPieStatisticsData.getCompileError());
		jsonReader.getQuery().getResponse().getStatistics().AddStatistics("IO", sqlRadarStatisticsData.getIO());
		jsonReader.getQuery().getResponse().getStatistics().AddStatistics("Array", sqlRadarStatisticsData.getArray());
		jsonReader.getQuery().getResponse().getStatistics().AddStatistics("Math", sqlRadarStatisticsData.getMath());
		jsonReader.getQuery().getResponse().getStatistics().AddStatistics("Sort", sqlRadarStatisticsData.getSort());
		jsonReader.getQuery().getResponse().getStatistics().AddStatistics("DataStructure", sqlRadarStatisticsData.getDataStructure());
	}
	private void runFriendListQuery() {
		sqlFriendData.runFriendListQuery(
				jsonReader.getInfo().getUID());
		for (String friendName : sqlFriendData.getFriendList()) {
			jsonReader.getQuery().getResponse().getFriendList().addFriend(friendName);
		}
	}
	private void runProblemRateQuery() {
		sqlProblemRate.runProblemRateQuery(
				jsonReader.getQuery().getQuestion().getProblemrate());
		jsonReader.getQuery().getResponse().getProblemRate().setAccept(sqlProblemRate.getAccept());
		jsonReader.getQuery().getResponse().getProblemRate().setPID(jsonReader.getQuery().getQuestion().getProblemrate());
		jsonReader.getQuery().getResponse().getProblemRate().setCompileError(sqlProblemRate.getCompileError());
		jsonReader.getQuery().getResponse().getProblemRate().setMemoryLimitExceeded(sqlProblemRate.getMemoryLimitExceeded());
		jsonReader.getQuery().getResponse().getProblemRate().setRuntimeError(sqlProblemRate.getRuntimeError());
		jsonReader.getQuery().getResponse().getProblemRate().setTimeLimitExceeded(sqlProblemRate.getTimeLimitExceeded());
		jsonReader.getQuery().getResponse().getProblemRate().setTotalSubmission(sqlProblemRate.getTotalSubmission());
		jsonReader.getQuery().getResponse().getProblemRate().setWrongAnswer(sqlProblemRate.getWrongAnswer());
	}
}
