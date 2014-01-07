package JSONTransmitProtocol.newReader.query;

public class Question {
	private String type;
	private int problemrate;
	private int rank;
	private String statistics;
	
	
	public Question() {}
	public Question(String type) {
		this.type = type;
	}
	
	public void setProblemrate(int problemrate) {
		this.problemrate = problemrate;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public void setStatistics(String statistics) {
		this.statistics = statistics;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getProblemrate() {
		return problemrate;
	}
	public int getRank() {
		return rank;
	}
	public String getStatistics() {
		return statistics;
	}
	public String getType() {
		return type;
	}
}
