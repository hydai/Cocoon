package cocoonServer.mysql;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLProblemType extends SQLData{
	private String querySQLString = "select * from ProblemType where pid = ?";
	public SQLProblemType() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cocoon?useUnicode=true&characterEncoding=UTF-8", "cocoonServer", "cocoon");

			dropdbSQL = "DROP TABLE ProblemType";
			createdbSQL = "CREATE TABLE ProblemType (" +
			"	pid INTEGER " + 
			",	type VARCHAR(256) )";
			insertdbSQL = "insert into ProblemType(pid, type) " +
			" VALUES ( ?, ?) ";
			selectSQL = "select * from ProblemType ";
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			close();
		}
	}
	public void insertTable(int pid, String type) {
		try {
			preparedStatement = connection.prepareStatement(insertdbSQL);
			preparedStatement.setInt(1, pid);
			preparedStatement.setString(2, type);
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
			System.out.print("pid\t\ttype\n");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("pid") + "\t\t" +
						resultSet.getString("type"));
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
	public String getProblemType(int pid) {
		String type = "";
		try {
			PreparedStatement getUsernamePreparedStatement = 
					connection.prepareStatement(querySQLString);
			getUsernamePreparedStatement.setInt(1, pid);
			ResultSet resultSet = getUsernamePreparedStatement.executeQuery();
			if (resultSet.next()) {
				type = resultSet.getString("type");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type;
	}
}
