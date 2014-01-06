package cocoonClient.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class GetProblemSet {
	private HashMap<String, Integer> map = new HashMap<String, Integer>();
	private Scanner scanner;
	public GetProblemSet() {
		try {
			scanner = new Scanner(new File("problem/problemData.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scanner.hasNextLine()) {
			int pid = scanner.nextInt();
			scanner.nextInt();
			scanner.nextInt();
			String problemName = scanner.nextLine();
			map.put(problemName, pid);
		}
	}
	public HashMap<String, Integer> getProblemSet(){
		return map;
	}
}
