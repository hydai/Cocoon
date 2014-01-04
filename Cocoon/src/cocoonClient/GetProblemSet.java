package cocoonClient;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class GetProblemSet {
	private HashMap<String, Integer> map = new HashMap<String, Integer>();
	public GetProblemSet() {
		map.put("The A+B Problem", 1);
		map.put("GCD", 2);
		map.put("Palindrome", 3);
	}
	public HashMap<String, Integer> getProblemSet(){
		return map;
	}
}
