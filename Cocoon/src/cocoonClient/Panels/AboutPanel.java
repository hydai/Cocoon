package cocoonClient.Panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cocoonClient.Data.UserInfo;

public class AboutPanel extends AbstractDisplayPanel{
	public AboutPanel(){
		super(UserInfo.getMainFrame());
		this.setLayout(new BorderLayout());
		// Show our Logo on north side of this panel
		setRightPanel(new TestRightPanel());
		this.add(new JLabel(new ImageIcon("res/About.png")), BorderLayout.NORTH);
	}
}
