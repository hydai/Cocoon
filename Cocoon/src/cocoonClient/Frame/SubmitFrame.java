package cocoonClient.Frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import cocoonClient.Data.UserInfo;
import cocoonClient.Panels.SubmitPanel;

public class SubmitFrame extends JFrame{
	public SubmitFrame() {
		this.setTitle("Submit Your Code Here");
		this.setResizable(false);
		this.setBounds(UserInfo.getMainFrame().getX()+UserInfo.getMainFrame().getWidth()/2-100,
				UserInfo.getMainFrame().getY()+UserInfo.getMainFrame().getHeight()/2-100,
				600, 500);
		this.setAlwaysOnTop(true);
		this.add(new SubmitPanel(this));
		this.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				close();
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void changeProblemName(String name){
		if(name.equals(""))
			this.setTitle("Submit Your Code Here");
		else
			this.setTitle("Submit Your Code Here (" + name + ")");
	}
	public void open(){
		this.setBounds(UserInfo.getMainFrame().getX()+UserInfo.getMainFrame().getWidth()/2-100,
				UserInfo.getMainFrame().getY()+UserInfo.getMainFrame().getHeight()/2-100,
				600, 500);
		this.setVisible(true);
	}
	public void close(){
		this.setVisible(false);
	}
}
