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
		//String[] run = { "/bin/sh", "-c", "./getOutput a.out" };
		String[] run = { "/bin/sh", "-c", "./antiskill -i input -o output a.out"};
		String[] check = { "/bin/sh", "-c", "./isCorrectOrNot output answer" };
		String[] clean = { "/bin/sh", "-c", "./clean" };
		try {
			Process p;
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			p = Runtime.getRuntime().exec(run);
			p.waitFor();
			p = Runtime.getRuntime().exec(check);
			p.waitFor();
			Scanner scanner = new Scanner(new File("result"));
			while (scanner.hasNext()) {
				String state = scanner.next();
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
