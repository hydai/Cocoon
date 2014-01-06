package cocoonClient.Frame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cocoonClient.Component.SwitchButton;
import cocoonClient.Connector.ChatClient;
import cocoonClient.Connector.LoginDialogue;
import cocoonClient.Data.UserInfo;
import cocoonClient.Panels.*;
import cocoonClient.Panels.AbstractRightPanel;
import cocoonClient.Panels.InfoPanel;
import cocoonClient.Panels.ProblemsPanel;
import cocoonClient.Panels.TestPanel;


public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final String ips[] = {"127.0.0.1", "140.114.200.157", "140.114.200.158", "140.114.200.159"};
	private InfoPanel infoPanel;
	private AbstractDisplayPanel displayPanel;
	private AbstractRightPanel rightPanel;
	private ChatClient client;
	
	public MainFrame() {
		UserInfo.initUserInfo(this, -1L);
		this.client = UserInfo.getClient();
		this.setTitle("Cocoon");
		this.setSize(800, 600);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocation(80,60);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMenu();
		initBtn();
		LoginDialogue loginDialogue = new LoginDialogue(this);
		//this.setVisible(true);
	}
	public void setRightPanel(AbstractRightPanel panel){
		if(rightPanel != null){
			rightPanel.setVisible(false);
		}
		this.rightPanel = panel;
		rightPanel.setVisible(true);
		this.add(rightPanel, BorderLayout.EAST);
		this.repaint();
	}
	public void setPanel(AbstractDisplayPanel panel){
		if(displayPanel != null){
			displayPanel.setVisible(false);
		}
		this.displayPanel = panel;
		displayPanel.setVisible(true);
		this.add(displayPanel, BorderLayout.WEST);
		displayPanel.getRightPanel().switchToThisPanel();
		this.repaint();
	}
	
	private void initBtn(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JButton[] buttons = new JButton[4];
		final String[] buttonTitle = new String[]{"Problem", "Status", "Info", "About"};
		AbstractDisplayPanel panels[] = new AbstractDisplayPanel[]{new ProblemsPanel(), new StatusPanel(), new TestPanel("Info"), new AboutPanel()};
		for (int i = 0; i < buttons.length; i++) {
			
			buttons[i] = new SwitchButton(this, panels[i], buttonTitle[i]);
			this.add(buttons[i]);
			buttons[i].setVisible(true);
			panel.add(buttons[i]);
		}
		this.add(panel, BorderLayout.SOUTH);
		panels[0].switchToThisPanel();
	}
	
	private void setMenu(){
		 JMenuBar jmb = new JMenuBar();
			setJMenuBar(jmb);
			jmb.setOpaque(true);
			JMenu jms = new JMenu("Set(S)");
			jms.setMnemonic('S');
			JMenu jmc = new JMenu("Connect(C)");
			JMenu change = new JMenu("Change IP(C)");
			change.setMnemonic('C');
			
			for(int i = 0; i < ips.length; i++){
				JMenuItem menuIp = new JMenuItem(ips[i]);
				final int index = i;
				menuIp.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						client.setIPAddress(ips[index]);
						client.setPort(8000);
						client.connect();
						if(client.isConnected())
							JOptionPane.showMessageDialog(null ,"Connectted Successfully!!"); 
						else
							JOptionPane.showMessageDialog(null, "Not Connected!!"); 
					}
				});
				change.add(menuIp);
			}
			
			JMenuItem connect = new JMenuItem("Reconnect(R)", KeyEvent.VK_R);
			connect.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					client.connect();
					if(client.isConnected())
						JOptionPane.showMessageDialog(null ,"Connectted Successfully!!"); 
					else
						JOptionPane.showMessageDialog(null, "Not Connected!!"); 
				}
			});
			jmc.add(connect);
			jmc.add(change);
			
			jms.add(jmc);
			jmb.add(jms);
	 }
}
