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
//	private String querySQLString = "select * from ProblemType where pid in (select pid from RadarStatistics where id = ?)";
	private String querySQLString = "select * from RadarStatistics where id = ?";
	private int id;
	private int IO;
	private int Math;
	private int Array;
	private int Sort;
	private int DataStructure;
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
			while (resultSet.next()) {
				int tmpPID = resultSet.getInt("pid");
				if (!solvedMap.containsKey(tmpPID)) {
					solvedMap.put(tmpPID, 1);
				}
			}
			for(Iterator<Entry<Integer, Integer>> iter = solvedMap.entrySet().iterator(); iter.hasNext();) {
				solvedProblem.add(iter.next().getKey());
			}
			
			IO = 0;
			Math = 0;
			Array = 0;
			Sort = 0;
			DataStructure = 0;
			
			/*
			while (resultSet.next()) {
				String typeString = resultSet.getString("type");
				if (typeString.equals("IO")) {
					IO++;
				}
				else if (typeString.equals("Math")) {
					Math++;
				}
				else if (typeString.equals("Array")) {
					Array++;
				}
				else if (typeString.equals("Sort")) {
					Sort++;
				}
				else if (typeString.equals("DataStructure")) {
					DataStructure++;
				}
			}
			*/
			SQLProblemType problemType = new SQLProblemType();
			for(int element : solvedProblem) {
				String typeString = problemType.getProblemType(element);
				if (typeString.equals("IO")) {
					IO++;
				}
				else if (typeString.equals("Math")) {
					Math++;
				}
				else if (typeString.equals("Array")) {
					Array++;
				}
				else if (typeString.equals("Sort")) {
					Sort++;
				}
				else if (typeString.equals("DataStructure")) {
					DataStructure++;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getId() {
		return id;
	}
	public int getArray() {
		return Array;
	}
	public int getDataStructure() {
		return DataStructure;
	}
	public int getIO() {
		return IO;
	}
	public int getMath() {
		return Math;
	}
	public int getSort() {
		return Sort;
	}
}
