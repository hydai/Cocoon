package cocoonServer;

import cocoonServer.mysql.SQLFriendData;
import cocoonServer.mysql.SQLProblemRate;
import JSONTransmitProtocol.newReader.JSONReader;

public class ServerQuery {
	private JSONReader jsonReader;
	private SQLProblemRate sqlProblemRate;
	private SQLFriendData sqlFriendData;
	@SuppressWarnings("unused")
	private ServerQuery() {
		// Do nothing
	}
	public ServerQuery(JSONReader reader) {
		this.jsonReader = reader;
		sqlProblemRate = new SQLProblemRate();
		sqlFriendData = new SQLFriendData();
	}
	public void run() {
		if (jsonReader.getQuery().getType().equals("problemrate")) {
			runProblemRateQuery();
		}
		else if (jsonReader.getQuery().getType().equals("friendlist")) {
			runFriendListQuery();
		}
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
