package cocoonClient;

import java.awt.FlowLayout;

import javax.swing.JLabel;

public class TestPanel extends AbstractDisplayPanel{
	public TestPanel(MainFrame parent, String title) {
		super(parent);
		setLayout(new FlowLayout());
		add(new JLabel(title));
	}
}
