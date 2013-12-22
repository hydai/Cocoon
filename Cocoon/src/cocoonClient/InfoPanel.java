package cocoonClient;
import javax.swing.JButton;
import javax.swing.JPanel;


public class InfoPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton[] buttons = new JButton[5];
	private final String[] buttonTitle = new String[]{"123", "456", "789", "haha", "UCCU"};
	
	public InfoPanel() {
		this.setSize(800, 100);
		this.setLayout(null);
		this.setVisible(true);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(buttonTitle[i]); 
			this.add(buttons[i]);
			buttons[i].setBounds(i * 160 + 6, 0, 140, 50);
			buttons[i].setVisible(true);
		}
	}
	
	
	
}
