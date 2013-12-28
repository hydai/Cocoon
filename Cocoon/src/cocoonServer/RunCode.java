package cocoonServer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class RunCode {
	private String language;
	private String result;
	private Long runtimeID;
	private int timeLimit;
	private int memoryLimit;
	private int problemID;
	private boolean isStrictJudge;
	public RunCode(String lang, Long runtimeID) {
		this.language = lang;
		this.runtimeID = runtimeID;
	}
	
	public void setMemoryLimit(int memoryLimit) {
		this.memoryLimit = memoryLimit;
	}
	public void setProblemID(int problemID) {
		this.problemID = problemID;
	}
	public void setStrictJudge(boolean isStrictJudge) {
		this.isStrictJudge = isStrictJudge;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getResult() {
		return result;
	}
	
	public void run() {
		String judgeFlag = "";
		if (isStrictJudge) {
			judgeFlag = "-s";
		}
		String[] cmd = { "/bin/sh", "-c", "sh /runtime/runJudge.sh " + judgeFlag + " -t "+timeLimit + " -m " + memoryLimit + " -c " + language + " -p " + problemID +" -r " + runtimeID};
		try {
			Process p;
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			Scanner scanner = new Scanner(new File("/runtime/"+runtimeID+".txt"));
			result = "";
			while (scanner.hasNext()) {
				result += (scanner.next() + "\n");
			}
			scanner.close();
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
