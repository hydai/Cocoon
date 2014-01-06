package cocoonClient.Panels;

import cocoonClient.Data.UserInfo;

public class AboutPanel extends AbstractDisplayPanel{
	public AboutPanel(){
		super(UserInfo.getMainFrame());
		setRightPanel(new TestRightPanel());
	}
}
