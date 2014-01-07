package cocoonServer.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLProblemData extends SQLData{
	public SQLProblemData() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cocoon?useUnicode=true&characterEncoding=UTF-8", "cocoonServer", "cocoon");

			dropdbSQL = "DROP TABLE Problem";
			createdbSQL = "CREATE TABLE Problem (" +
			"	pid	INTEGER " + 
			",	title	VARCHAR(256) " +
			",	timeLimit	INTEGER " +
			",	memoryLimit INTEGER)";
			insertdbSQL = "insert into Problem(pid,title,timeLimit,memoryLimit) " +
			" ?, ?, ?, ? ";
			selectSQL = "select * from Problem ";
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			close();
		}
	}
	public void insertTable(int pid, String title, int timeLimit, int memoryLimit) {
		try {
			preparedStatement = connection.prepareStatement(insertdbSQL);
			preparedStatement.setInt(1, pid);
			preparedStatement.setString(2, title);
			preparedStatement.setInt(3, timeLimit);
			preparedStatement.setInt(4, memoryLimit);
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
			System.out.print("PID\t\tTitle\t\tTL\t\tML\n");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("pid") + "\t\t" +
						resultSet.getString("title") + "\t\t" +
						resultSet.getInt("timeLimit") + "\t\t" +
						resultSet.getInt("memoryLimit"));
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
}
