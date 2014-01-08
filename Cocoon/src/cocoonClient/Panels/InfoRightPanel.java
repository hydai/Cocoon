package cocoonClient.Panels;

import java.awt.BorderLayout;

import cocoonClient.Connector.AbstractConnector;
import cocoonClient.Data.UserInfo;

public class InfoRightPanel extends CocoonRightPanel implements AbstractConnector{
	public InfoRightPanel(){
		super(UserInfo.getMainFrame());
		UserInfo.getPanels().put("friendlist", this);
		this.setLayout(new BorderLayout());
	}

	@Override
	public void recieveResponse(String response) {
		
	}
	
}
