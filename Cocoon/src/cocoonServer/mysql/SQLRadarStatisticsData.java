package cocoonServer.mysql;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SQLRadarStatisticsData extends SQLData{
	private String querySQLString = "select * from RadarStatistics where id = ?";
	private int id;
	private ArrayList<Integer> solvedProblem;
	public SQLRadarStatisticsData() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cocoon?useUnicode=true&characterEncoding=UTF-8", "cocoonServer", "cocoon");

			dropdbSQL = "DROP TABLE RadarStatistics";
			createdbSQL = "CREATE TABLE RadarStatistics (" +
			"	id INTEGER " + 
			",	pid INTEGER )";
			insertdbSQL = "insert into RadarStatistics(id,pid) " +
			" VALUES ( ?, ?) ";
			selectSQL = "select * from RadarStatistics ";
			solvedProblem = new ArrayList<Integer>();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			close();
		}
	}
	public void insertTable(int id,int pid) {
		try {
			preparedStatement = connection.prepareStatement(insertdbSQL);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, pid);
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
			System.out.print("id\t\tpid\n");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("id") + "\t\t" +
						resultSet.getInt("pid"));
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
	
	public void runRadarStatisticsQuery(int id) {
		this.id = id;
		try {
			PreparedStatement getUsernamePreparedStatement = 
					connection.prepareStatement(querySQLString);
			getUsernamePreparedStatement.setInt(1, id);
			ResultSet resultSet = getUsernamePreparedStatement.executeQuery();
			Map<Integer, Integer> solvedMap = new TreeMap<Integer, Integer>();
			if (resultSet.next()) {
				int tmpPID = resultSet.getInt("pid");
				if (!solvedMap.containsKey(tmpPID)) {
					solvedMap.put(tmpPID, 1);
				}
			}
			for(Iterator<Entry<Integer, Integer>> iter = solvedMap.entrySet().iterator(); iter.hasNext();) {
				solvedProblem.add(iter.next().getKey());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getId() {
		return id;
	}
	public ArrayList<Integer> getSolvedProblem() {
		return solvedProblem;
	}
}
