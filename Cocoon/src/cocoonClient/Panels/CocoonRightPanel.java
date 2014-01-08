package cocoonClient.Panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import cocoonClient.Frame.MainFrame;

public class CocoonRightPanel extends JPanel{
	protected MainFrame parent;
	public CocoonRightPanel(MainFrame parent) {
		super();
		setSize(200, 500);
		setOpaque(false);
		setBackground(new Color(0, 0, 0, 0));
		this.parent = parent;
	}
	public void switchToThisPanel(){
		parent.setRightPanel(this);
	}
}
