package cocoonClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class SwitchButton extends JButton{
	private MainFrame parent;
	private AbstractDisplayPanel panel;
	//When clicked, mainframe will switch to the panel it stands for.
	SwitchButton(MainFrame parent, final AbstractDisplayPanel panel, String title){
		super(title);
		this.parent = parent;
		this.panel = panel;
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.switchToThisPanel();
			}
		});
	}
}
