package cocoonClient;
import org.json.*;


public class JSONCreater {
	private JSONObject obj = new JSONObject();
	private JSONObject submission = new JSONObject();
	private JSONObject query = new JSONObject();
	private JSONObject login = new JSONObject();
	private JSONCreater(){
		try{
			obj.put("type", "");
			obj.put("submission", submission);
			obj.put("query", query);
			obj.put("login", login);
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	JSONCreater(String type, String language, String code){
		this();
		try {
			obj.put("type", type);
			addCode(language, code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void addCode(String language, String code) throws JSONException{
		JSONObject sub = (JSONObject) obj.get("submission");
		sub.put("language", language);
		sub.put("code", code);
	}
	public JSONObject getJSONObject(){
		return obj;
	}
}
