package cocoonServer.initializeServer;

import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateUserData extends CreateData{
	public CreateUserData() {
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
}
