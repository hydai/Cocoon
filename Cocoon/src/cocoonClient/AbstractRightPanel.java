package cocoonClient;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class AbstractRightPanel extends JPanel{
	protected MainFrame parent;
	public AbstractRightPanel(MainFrame parent) {
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
