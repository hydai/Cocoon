package cocoonClient;

import java.awt.Color;

import javax.swing.*;

public class AbstractDisplayPanel extends JPanel{
	protected MainFrame parent;
	protected AbstractRightPanel rightPanel;
	public AbstractDisplayPanel(MainFrame parent) {
		super();
		setSize(600, 500);
		setOpaque(false);
		setBackground(new Color(0, 0, 0, 0));
		this.parent = parent;
	}
	public void switchToThisPanel(){
		parent.setPanel(this);
	}
	protected void setRightPanel(AbstractRightPanel panel){
		rightPanel = panel;
	}
	public AbstractRightPanel getRightPanel(){
		return rightPanel;
	}
}
