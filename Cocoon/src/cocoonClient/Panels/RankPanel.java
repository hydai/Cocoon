package cocoonClient.Panels;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import JSONTransmitProtocol.newReader.JSONReader;
import cocoonClient.Connector.AbstractConnector;
import cocoonClient.Data.UserInfo;
import cocoonClient.Frame.MainFrame;

public class RankPanel extends CocoonRightPanel implements AbstractConnector{

	public RankPanel(MainFrame parent) {
		super(parent);
		UserInfo.getPanels().put("rank", this);
	}

	@Override
	public void recieveResponse(String response) {
		Map<String, Integer>rank = new JSONReader(response).getQuery().getResponse().getRank().getRankMap();
		for(Entry<String, Integer> entry :rank.entrySet()){
			
		}
	}

}
