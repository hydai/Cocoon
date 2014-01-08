package cocoonClient.Panels;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import cocoonClient.Data.UserInfo;

public class TestRightPanel extends CocoonRightPanel{
	TestRightPanel(){
		super(UserInfo.getMainFrame());
		setLayout(new FlowLayout());
		this.add(new JLabel(""));
	}
}
