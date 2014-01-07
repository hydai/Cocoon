package cocoonServer;

public class RuntimeID {
	private static int runtimeID;
	private static RuntimeID self;
	private RuntimeID() {
		runtimeID = 0;
	}
	public static synchronized RuntimeID getInstance() {
		if (self == null) {
			self = new RuntimeID();
		}
		return self;
	}
	public synchronized int getRuntimeID() {
		runtimeID++;
		return runtimeID;
	}
}
