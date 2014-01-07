package cocoonServer.mysql;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLProblemRate extends SQLData{
	private String querySQLString = "select * from ProblemRate where pid = ?";
	private int pid;
	private int totalSubmission;
	private int accept;
	private int wrongAnswer;
	private int runtimeError;
	private int timeLimitExceeded;
	private int memoryLimitExceeded;
	private int compileError;
	public SQLProblemRate() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cocoon?useUnicode=true&characterEncoding=UTF-8", "cocoonServer", "cocoon");

			dropdbSQL = "DROP TABLE ProblemRate";
			createdbSQL = "CREATE TABLE ProblemRate (" +
			"	pid INTEGER " + 
			",	totalSubmission INTEGER " +
			",	accept INTEGER " +
			",	wrongAnswer INTEGER " +
			",	runtimeError INTEGER " +
			",	timeLimitExceeded INTEGER " +
			",	memoryLimitExceeded INTEGER " +
			",	compileError INTEGER )";
			insertdbSQL = "insert into ProblemRate(pid,totalSubmission,accept,wrongAnswer,runtimeError,timeLimitExceeded,memoryLimitExceeded,compileError) " +
			" VALUES ( ?, ?, ?, ?, ?, ?, ?, ? ) ";
			selectSQL = "select * from ProblemRate ";
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			close();
		}
	}
	public void insertTable(
		int pid,
		int totalSubmission,
		int accept,
		int wrongAnswer,
		int runtimeError,
		int timeLimitExceeded,
		int memoryLimitExceeded,
		int compileError) {
		try {
			preparedStatement = connection.prepareStatement(insertdbSQL);
			preparedStatement.setInt(1, pid);
			preparedStatement.setInt(2, totalSubmission);
			preparedStatement.setInt(3, accept);
			preparedStatement.setInt(4, wrongAnswer);
			preparedStatement.setInt(5, runtimeError);
			preparedStatement.setInt(6, timeLimitExceeded);
			preparedStatement.setInt(7, memoryLimitExceeded);
			preparedStatement.setInt(8, compileError);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			close();
		}
	}

	public void selectTable() {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectSQL);
			System.out.print("pid\t\ttotalSubmission\t\taccept\t\twrongAnswer\t\truntimeError\t\ttimeLimitExceeded\t\tmemoryLimitExceeded\t\tcompileError\n");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("pid") + "\t\t" +
						resultSet.getInt("totalSubmission") + "\t\t" +
						resultSet.getInt("accept") + "\t\t" +
						resultSet.getInt("wrongAnswer") + "\t\t" +
						resultSet.getInt("runtimeError") + "\t\t" +
						resultSet.getInt("timeLimitExceeded") + "\t\t" +
						resultSet.getInt("memoryLimitExceeded") + "\t\t" +
						resultSet.getInt("compileError"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			close();
		}
	}
	
	public void runProblemRateQuery(int pid) {
		try {
			PreparedStatement getUsernamePreparedStatement = 
					connection.prepareStatement(querySQLString);
			getUsernamePreparedStatement.setInt(1, pid);
			ResultSet resultSet = getUsernamePreparedStatement.executeQuery();
			if (resultSet.next()) {
				totalSubmission = resultSet.getInt("totalSubmission");
				accept = resultSet.getInt("accept");
				wrongAnswer = resultSet.getInt("wrongAnswer");
				runtimeError = resultSet.getInt("runtimeError");
				timeLimitExceeded = resultSet.getInt("timeLimitExceeded");
				memoryLimitExceeded = resultSet.getInt("memoryLimitExceeded");
				compileError = resultSet.getInt("compileError");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getAccept() {
		return accept;
	}
	public int getCompileError() {
		return compileError;
	}
	public int getMemoryLimitExceeded() {
		return memoryLimitExceeded;
	}
	public int getPid() {
		return pid;
	}
	public int getRuntimeError() {
		return runtimeError;
	}
	public int getTotalSubmission() {
		return totalSubmission;
	}
	public int getTimeLimitExceeded() {
		return timeLimitExceeded;
	}
	public int getWrongAnswer() {
		return wrongAnswer;
	}
}
/*
 				"PID":<int>
				"TotalSubmission":<int>
				"Accept":<int>
				"WrongAnswer":<int>
				"RuntimeError":<int>
				"TimeLimitExceeded":<int>
				"MemoryLimitExceeded":<int>
				"compileError":<int>
 * */
