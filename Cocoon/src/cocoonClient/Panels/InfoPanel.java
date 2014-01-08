package cocoonClient.Panels;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import JSONTransmitProtocol.newcreater.JSONCreater;
import JSONTransmitProtocol.newcreater.query.CreaterQuery;
import JSONTransmitProtocol.newcreater.query.QueryQuestion;
import cocoonClient.Component.SwitchButton;
import cocoonClient.Data.UserInfo;
import cocoonClient.Frame.MainFrame;



public class InfoPanel extends CocoonDisplayPanel{	
	public static int RADARGRAPH = 1, PIEGRAPH = 2;
	private static final long serialVersionUID = 1L;
	private SpiderWebGraph radargragh;
	private PieGraph pieGraph;
	public InfoPanel() {
		super(UserInfo.getMainFrame());
		this.setPreferredSize(new Dimension(500, 600));
		this.setLayout(new BorderLayout());
		radargragh = new SpiderWebGraph();
		pieGraph = new PieGraph();
		this.add(radargragh, BorderLayout.CENTER);
		this.setRightPanel(new InfoRightPanel(this));
	}
	public void changeGraph(int mode){
		System.out.println("InfoPanel: " + mode);
		this.removeAll();
		this.setLayout(new BorderLayout());
		if(mode == 1){
			this.add(radargragh,BorderLayout.CENTER);
		}
		else if (mode == 2) {
			this.add(pieGraph, BorderLayout.CENTER);
		}
		this.repaint();
	}
	@Override
	public void switchToThisPanel(){
		super.switchToThisPanel();
		JSONCreater json = new JSONCreater("query")
				.setInfo(UserInfo.getUserInfo())
				.setQuery(new CreaterQuery("question", new QueryQuestion("statistics", UserInfo.getUsername())));
		UserInfo.getClient().sendMessage(json.toString());
		json = new JSONCreater("query")
		.setInfo(UserInfo.getUserInfo())
		.setQuery(new CreaterQuery("question", new QueryQuestion("friendlist", UserInfo.getUsername())));
		UserInfo.getClient().sendMessage(json.toString());
	}
	
	
}
