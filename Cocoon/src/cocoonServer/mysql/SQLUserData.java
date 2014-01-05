package cocoonServer.mysql;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserData extends SQLData{
	private String checkUserExistSQL = "";
	public SQLUserData() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cocoon?useUnicode=true&characterEncoding=UTF-8", "cocoonServer", "cocoon");

			dropdbSQL = "DROP TABLE User";
			createdbSQL = "CREATE TABLE User (" +
			"	id	INTEGER " + 
			",	name	VARCHAR(128) " +
			",	passwd VARCHAR(50))";
			insertdbSQL = "insert into User(id,name,passwd) " +
			"select ifNULL(max(id),0)+1, ?, ? FROM User";
			selectSQL = "select * from User ";
			checkUserExistSQL = "select * from User where name = '?' ";
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			close();
		}
	}
	public void insertTable(String name, String passwd) {
		try {
			preparedStatement = connection.prepareStatement(insertdbSQL);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, passwd);
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
			System.out.print("ID\t\tName\t\t\t\tPASSWORD\n");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("id") + "\t\t" +
						resultSet.getString("name") + "\t\t\t\t" +
						resultSet.getString("passwd"));
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
	public boolean isUserExist(String username) {
		boolean isExist = false;
		try {
			PreparedStatement checkUserExistPreparedStatement = 
					connection.prepareStatement(checkUserExistSQL);
			checkUserExistPreparedStatement.setString(1, username);
			ResultSet resultSet = checkUserExistPreparedStatement.executeQuery();
			if (resultSet.getRow() == 0) {
				isExist = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isExist;
	}
	public int getUserId(String username) {
		int uid = -1;
		String getUserIdSQL = "select * from User where name = '?' ";
		try {
			PreparedStatement getUserIdPreparedStatement =
					connection.prepareStatement(getUserIdSQL);
			getUserIdPreparedStatement.setString(1, username);
			ResultSet resultSet = getUserIdPreparedStatement.executeQuery();
			if (resultSet.next()) {
				uid = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uid;
	}
	public int checkUserPassword(String username, String password) {
		int uid = -1;
		String checkUserPasswordSQL = "select * from User where name = '?' AND passwd = '?' ";
		try {
			PreparedStatement checkUserPasswordPreparedStatement = 
					connection.prepareStatement(checkUserPasswordSQL);
			checkUserPasswordPreparedStatement.setString(1, username);
			checkUserPasswordPreparedStatement.setString(2, password);
			ResultSet resultSet = checkUserPasswordPreparedStatement.executeQuery();
			if (resultSet.next()) {
				uid = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uid;
	}
}
