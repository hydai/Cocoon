package cocoonClient.Panels;

import cocoonClient.Connector.AbstractConnector;
import cocoonClient.Data.UserInfo;
import cocoonClient.Frame.MainFrame;

public class RankPanel extends AbstractRightPanel implements AbstractConnector{

	public RankPanel(MainFrame parent) {
		super(parent);
		UserInfo.getPanels().put("rank", this);
	}

	@Override
	public void recieveResponse(String response) {
		
		//TODO
	}

}
