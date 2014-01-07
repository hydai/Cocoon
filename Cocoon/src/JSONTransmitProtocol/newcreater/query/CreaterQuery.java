package JSONTransmitProtocol.newcreater.query;

import org.json.JSONObject;

public class CreaterQuery extends JSONObject{
	public CreaterQuery(String type, QueryQuestion question){
		super();
		try{
			this.put("type", type);
			this.put("question", question);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public CreaterQuery(String type, QueryResponse response){
		super();
		try{
			this.put("type", type);
			this.put("response", response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
