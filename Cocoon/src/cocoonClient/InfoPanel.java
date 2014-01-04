package cocoonClient;
import javax.swing.*;



public class InfoPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton[] buttons = new JButton[5];
	private final String[] buttonTitle = new String[]{"Play", "Status", "Submit", "Info", "Set"};
	private MainFrame parent;
	public InfoPanel(MainFrame parent) {
		this.parent = parent;
		this.setSize(800, 100);
		this.setLayout(null);
		this.setVisible(true);
		for (int i = 0; i < buttons.length; i++) {
			if(i != 2)
				buttons[i] = new SwitchButton(parent, new TestPanel(buttonTitle[i]), buttonTitle[i]);
			
			this.add(buttons[i]);
			buttons[i].setBounds(i * 160 + 6, 0, 140, 50);
			buttons[i].setVisible(true);
		}
	}
	
	
	
}
