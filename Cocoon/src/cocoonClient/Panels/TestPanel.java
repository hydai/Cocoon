package cocoonClient.Panels;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import cocoonClient.Data.UserInfo;

public class TestPanel extends AbstractDisplayPanel{
	public TestPanel(String title) {
		super(UserInfo.getMainFrame());
		setLayout(new FlowLayout());
		Browser web = new Browser();
		web.setVisible(true);
		web.loadURL("https://dl.dropboxusercontent.com/u/113630504/OJ/1.htm");
		add(web);
		setRightPanel(new TestRightPanel());
	}
}
