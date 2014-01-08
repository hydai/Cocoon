package cocoonServer.mysql;

import java.util.Scanner;

public class LinkToMySQL {
	public static void main(String[] args) {
		/*
		SQLFriendDate friendDate = new SQLFriendDate();
		friendDate.dropTable();
		friendDate.createTable();
		System.out.println(LinkToMySQL.class.getResourceAsStream("friendListData.txt"));
		Scanner scanner = new Scanner(LinkToMySQL.class.getResourceAsStream("friendListData.txt"));
		while (scanner.hasNext()) {
			int id, friendID;
			id = scanner.nextInt();
			friendID = scanner.nextInt();
			friendDate.insertTable(id, friendID);
		}
		scanner.close();
		friendDate.selectTable();
		*/
		/*
		SQLUserData userData = new SQLUserData();
		userData.dropTable();
		userData.createTable();
		System.out.println(LinkToMySQL.class.getResourceAsStream("userData.txt"));
		Scanner scanner = new Scanner(LinkToMySQL.class.getResourceAsStream("userData.txt"));
		while (scanner.hasNext()) {
			String name, passwd;
			name = scanner.next();
			passwd = scanner.next();
			userData.insertTable(name, passwd);
		}
		scanner.close();
		userData.selectTable();
		
		
		SQLProblemRate problemRate = new SQLProblemRate();
		problemRate.dropTable();
		problemRate.createTable();
		System.out.println(LinkToMySQL.class.getResourceAsStream("problemRateData.txt"));
		scanner = new Scanner(LinkToMySQL.class.getResourceAsStream("problemRateData.txt"));
		while (scanner.hasNext()) {
			int pid = scanner.nextInt();
			int totalSubmission = scanner.nextInt();
			int accept = scanner.nextInt();
			int wrongAnswer = scanner.nextInt();
			int runtimeError = scanner.nextInt();
			int timeLimitExceeded = scanner.nextInt();
			int memoryLimitExceeded = scanner.nextInt();
			int compileError = scanner.nextInt();
			problemRate.insertTable(pid, totalSubmission, accept, wrongAnswer, runtimeError, timeLimitExceeded, memoryLimitExceeded, compileError);
		}
		scanner.close();
		problemRate.selectTable();
		*/
		/*
		SQLProblemData problemData = new SQLProblemData();
		problemData.dropTable();
		problemData.createTable();
		scanner = new Scanner(LinkToMySQL.class.getResourceAsStream("problemData.txt"));
		while (scanner.hasNext()) {
			String title;
			int pid, timeLimit, memoryLimit;
			pid = scanner.nextInt();
			timeLimit = scanner.nextInt();
			memoryLimit = scanner.nextInt();
			title = scanner.next();
			problemData.insertTable(pid, title, timeLimit, memoryLimit);
		}
		scanner.close();
		problemData.selectTable();
		*/
	}
}
