package cocoonClient.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cocoonClient.Frame.MainFrame;
import cocoonClient.Panels.CocoonDisplayPanel;
public class SwitchButton extends CocoonButton{
	private MainFrame parent;
	private CocoonDisplayPanel panel;
	//When clicked, mainframe will switch to the panel it stands for.
	public SwitchButton(MainFrame parent, final CocoonDisplayPanel panel, String title){
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
