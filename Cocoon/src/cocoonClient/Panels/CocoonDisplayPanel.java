package cocoonClient.Panels;

import java.awt.Color;

import javax.swing.*;

import cocoonClient.Frame.MainFrame;

public class CocoonDisplayPanel extends JPanel{
	protected MainFrame parent;
	protected CocoonRightPanel rightPanel;
	public CocoonDisplayPanel(MainFrame parent) {
		super();
		setSize(600, 500);
		setOpaque(false);
		setBackground(new Color(0, 0, 0, 0));
		this.parent = parent;
	}
	public void switchToThisPanel(){
		parent.setPanel(this);
	}
	protected void setRightPanel(CocoonRightPanel panel){
		rightPanel = panel;
	}
	public CocoonRightPanel getRightPanel(){
		return rightPanel;
	}
}
