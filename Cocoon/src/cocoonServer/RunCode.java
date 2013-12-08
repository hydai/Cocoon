package cocoonServer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class RunCode {
	String fileName;
	String language;
	public RunCode(String fileString, String lang) {
		this.fileName = fileString;
		this.language = lang;
	}
	
	public void run() {
		String[] cmd = { "/bin/sh", "-c", "clang++ -static code.cpp -o a.out" };
		String[] run = { "/bin/sh", "-c", "./antiskill -t 1000 -i input -o output a.out > runtime"};
		String[] diff = { "/bin/sh", "-c", "diff output answer > tmpdiff" };
		String[] check = { "/bin/sh", "-c", "./isCorrectOrNot tmpdiff" };
		String[] clean = { "/bin/sh", "-c", "./clean" };
		try {
			Process p;
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			p = Runtime.getRuntime().exec(run);
			p.waitFor();
			p = Runtime.getRuntime().exec(diff);
			p.waitFor();
			p = Runtime.getRuntime().exec(check);
			p.waitFor();
			Scanner scanner;
			scanner = new Scanner(new File("runtime"));
			String state = "";
			while (scanner.hasNext())
				state += scanner.next();
			System.out.println(state);
			if (state.equals("TimeLimitExceeded"))
				System.out.println("Time Limit Exceeded");
			else {
				scanner = new Scanner(new File("result"));
				state = "";
				while (scanner.hasNext()) 
					state += scanner.next();
				System.out.println(state);
			}
			p = Runtime.getRuntime().exec(clean);
			p.waitFor();
			System.out.println("Done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
