package cocoonServer;

/**
 * This class is to generate unique runtime id for each 
 * submission.
 * @author hydai
 *
 */
public class RuntimeID {
	private static int runtimeID;
	private static RuntimeID self;
	private RuntimeID() {
		runtimeID = 0;
	}
	/**
	 * Use singleton and thread safety way to generate one
	 * and only one instance to make sure each runtime id
	 * is absolutely unique.
	 * @return instance of RuntimeID
	 */
	public static synchronized RuntimeID getInstance() {
		if (self == null) {
			self = new RuntimeID();
		}
		return self;
	}
	/**
	 * Return the runtime id and make add count by one.
	 * @return the runtimeID
	 */
	public synchronized int getRuntimeID() {
		runtimeID++;
		return runtimeID;
	}
}
