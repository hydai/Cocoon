package cocoonClient.Connector;

import JSONTransmitProtocol.newReader.JSONReader;
import cocoonClient.Data.UserInfo;

public class Distributor {
	public static void distribute(String line){
		JSONReader reader = new JSONReader(line);
		String type = reader.getType();
		if(type.equals("query")){
			String subtype = reader.getQuery().getResponse().getType();
			UserInfo.getPanels().get(subtype).recieveResponse(line);
		}
		else if(type.equals("submission")){
			UserInfo.getPanels().get("SubmissionResponse").recieveResponse(line);
		}
		else if(type.equals("login")){
			UserInfo.getPanels().get(type).recieveResponse(line);
		}
	}
}
