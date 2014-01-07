package JSONTransmitProtocol.newcreater;

import org.json.JSONObject;

import JSONTransmitProtocol.newcreater.info.CreaterInfo;
import JSONTransmitProtocol.newcreater.login.CreaterLogin;
import JSONTransmitProtocol.newcreater.query.CreaterQuery;
import JSONTransmitProtocol.newcreater.submission.CreaterSubmission;

public class JSONCreater extends JSONObject{
	
	
	public JSONCreater(String type){
		super();
		try{
			this.put("type", type);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public JSONCreater setInfo(CreaterInfo info){
		try{
			this.put("info", info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return this;
	}
	public JSONCreater setSubmission(CreaterSubmission submission){
		try{
			this.put("submission", submission);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return this;
	}
	public JSONCreater setLogin(CreaterLogin login){
		try{
			this.put("login", login);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return this;
	}
	public JSONCreater setQuery(CreaterQuery query){
		try{
			this.put("info", query);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return this;
	}
}
