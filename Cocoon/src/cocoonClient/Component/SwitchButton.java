package cocoonClient.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cocoonClient.Frame.MainFrame;
import cocoonClient.Panels.AbstractDisplayPanel;
public class SwitchButton extends CocoonButton{
	private MainFrame parent;
	private AbstractDisplayPanel panel;
	//When clicked, mainframe will switch to the panel it stands for.
	public SwitchButton(MainFrame parent, final AbstractDisplayPanel panel, String title){
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
