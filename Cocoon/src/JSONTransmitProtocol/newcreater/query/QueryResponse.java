package JSONTransmitProtocol.newcreater.query;

import org.json.JSONArray;
import org.json.JSONObject;

public class QueryResponse extends JSONObject{
	public QueryResponse(String type, ResponseProblemrate problemrate){
		super();
		try{
			this.put("type", type);
			this.put("problemrate", problemrate);
		}
		catch(Exception e){
			
		}
	}
	public QueryResponse(String type, JSONArray array){
		super();
		try{
			this.put("type", type);
			this.put(type, array);
		}
		catch(Exception e){
			
		}
	
	}
}
