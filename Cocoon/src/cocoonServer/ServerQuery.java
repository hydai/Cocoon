package cocoonServer;

import cocoonServer.mysql.SQLProblemRate;
import JSONTransmitProtocol.newReader.JSONReader;

public class ServerQuery {
	private JSONReader jsonReader;
	private SQLProblemRate sqlProblemRate;
	@SuppressWarnings("unused")
	private ServerQuery() {
		// Do nothing
	}
	public ServerQuery(JSONReader reader) {
		this.jsonReader = reader;
		sqlProblemRate = new SQLProblemRate();
	}
	public void run() {
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
