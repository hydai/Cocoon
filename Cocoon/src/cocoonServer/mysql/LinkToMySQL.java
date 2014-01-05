package cocoonServer.mysql;

import java.util.Scanner;

public class LinkToMySQL {
	public static void main(String[] args) {
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
