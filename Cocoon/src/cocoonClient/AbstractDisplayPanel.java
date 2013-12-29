package cocoonClient;

import javax.swing.*;

public class AbstractDisplayPanel extends JPanel{
	protected MainFrame parent;
	public AbstractDisplayPanel(MainFrame parent) {
		super();
		setSize(600, 500);
		this.parent = parent;
	}
	public void switchToThisPanel(){
		parent.setPanel(this);
	}
}
