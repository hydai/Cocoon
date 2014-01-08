package cocoonServer.mysql;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLFriendData extends SQLData{
	private String querySQLString = "select * from User where id in (select friendID from FriendList where id = ?)";
	private int selfUid;
	private ArrayList<String> friendList;
	public SQLFriendData() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cocoon?useUnicode=true&characterEncoding=UTF-8", "cocoonServer", "cocoon");

			dropdbSQL = "DROP TABLE FriendList";
			createdbSQL = "CREATE TABLE FriendList (" +
			"	id INTEGER " +
			",	friendID INTEGER )";
			insertdbSQL = "insert into FriendList(id, friendID) " +
			" VALUES ( ?, ?) ";
			selectSQL = "select * from FriendList ";
			friendList = new ArrayList<String>();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			close();
		}
	}
	public void insertTable(int uid, int friendID) {
			try {
				preparedStatement = connection.prepareStatement(insertdbSQL);
				preparedStatement.setInt(1, uid);
				preparedStatement.setInt(2, friendID);
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
			System.out.println("id\t\tfriendID");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("id") + "\t\t" +
						resultSet.getInt("friendID"));
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
	
	public void runFriendListQuery(int id) {
		selfUid = id;
		try {
			PreparedStatement getUsernamePreparedStatement = 
					connection.prepareStatement(querySQLString);
			getUsernamePreparedStatement.setInt(1, id);
			ResultSet resultSet = getUsernamePreparedStatement.executeQuery();
			friendList = new ArrayList<String>();
			while (resultSet.next()) {
				friendList.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<String> getFriendList() {
		return friendList;
	}
	public int getSelfUid() {
		return selfUid;
	}
}
