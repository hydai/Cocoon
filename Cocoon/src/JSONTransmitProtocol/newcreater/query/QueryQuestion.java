package JSONTransmitProtocol.newcreater.query;

import org.json.JSONObject;

public class QueryQuestion extends JSONObject{
	public QueryQuestion(String type, int num){
		super();
		try{
			this.put("type", type);
			this.put(type, num);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public QueryQuestion(String type, String str){
		super();
		try{
			this.put("type", type);
			this.put(type, str);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/*public QueryQuestion setUID(int UID){
		try{
			this.put("UID", UID);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return this;
	}
	public QueryQuestion setRank(int rank){
		try{
			this.put("rank", rank);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return this;
	}
	public QueryQuestion setStatistics(String username){
		try{
			this.put("statistics", username);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return this;
	}*/
}
