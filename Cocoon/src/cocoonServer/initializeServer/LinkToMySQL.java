package cocoonServer.initializeServer;

import java.io.File;
import java.util.Scanner;

public class LinkToMySQL {
	public static void main(String[] args) {
		CreateUserData userData = new CreateUserData();
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
	}
}
