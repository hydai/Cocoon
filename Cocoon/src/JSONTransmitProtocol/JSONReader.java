package JSONTransmitProtocol;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONReader{
	/** transform json file to String */
	private String jsonString;
	private Map<String, String> submissionMap;
	public JSONReader(String jsonString) {
		this.jsonString = jsonString;
		System.out.println(this.jsonString);
		submissionMap = new TreeMap<String, String>();
		init();
	}
	
	/**
	 * Parse the json file to EarthquakesInformation.
	 */
	private void init() {
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			String type = jsonObject.getString("type");
			if (type.equals("submission")) {
				JSONObject submission = jsonObject.getJSONObject("submission");
				submissionMap.put("language", submission.getString("language"));
				submissionMap.put("code", submission.getString("code"));
			}
			else {
				System.out.println("Unknown Error!");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getLanguage() {
		return submissionMap.get("language");
	}
	
	public String getCode() {
		return submissionMap.get("code");
	}
}
