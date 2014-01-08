package cocoonClient.Panels;
import java.awt.BorderLayout;

import javax.swing.*;

import JSONTransmitProtocol.newcreater.JSONCreater;
import JSONTransmitProtocol.newcreater.query.CreaterQuery;
import JSONTransmitProtocol.newcreater.query.QueryQuestion;
import cocoonClient.Component.SwitchButton;
import cocoonClient.Data.UserInfo;
import cocoonClient.Frame.MainFrame;



public class InfoPanel extends CocoonDisplayPanel{	
	private static final long serialVersionUID = 1L;
	private SpiderWebGraph gragh;
	public InfoPanel() {
		super(UserInfo.getMainFrame());
		this.setLayout(new BorderLayout());
		gragh = new SpiderWebGraph();
		this.add(gragh, BorderLayout.NORTH);
		this.setRightPanel(new InfoRightPanel());
	}
	@Override
	public void switchToThisPanel(){
		super.switchToThisPanel();
		JSONCreater json = new JSONCreater("query")
				.setInfo(UserInfo.getUserInfo())
				.setQuery(new CreaterQuery("question", new QueryQuestion("statistics", UserInfo.getUsername())));
		//UserInfo.getClient().sendMessage(json.toString());
		json = new JSONCreater("query")
		.setInfo(UserInfo.getUserInfo())
		.setQuery(new CreaterQuery("question", new QueryQuestion("friendlist", UserInfo.getUsername())));
		UserInfo.getClient().sendMessage(json.toString());
	}
	
	
}
