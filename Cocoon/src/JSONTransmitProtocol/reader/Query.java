package JSONTransmitProtocol.reader;

public class Query {
	private String type;
	
	@SuppressWarnings("unused")
	private Query() {
		type = "";
	}
	
	public Query(String typeString) {
		type = typeString;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
}
