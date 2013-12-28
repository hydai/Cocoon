package cocoonServer;

public class RuntimeID {
	private static Long runtimeID;
	private static RuntimeID self;
	private RuntimeID() {
		runtimeID = 0L;
	}
	public static synchronized RuntimeID getInstance() {
		if (self == null) {
			self = new RuntimeID();
		}
		return self;
	}
	public synchronized Long getRuntimeID() {
		runtimeID++;
		return runtimeID;
	}
}
