package cocoonClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String ips[] = {"127.0.0.1", "140.114.200.157", "140.114.200.158", "140.114.200.159"};
	private InfoPanel infoPanel;
	private AbstractDisplayPanel displayPanel;
	private ChatClient client;
	private SubmitPanel sp;
	
	public MainFrame() {
		this.setTitle("Cocoon");
		this.setSize(800, 600);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(80,60);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMenu();
		infoPanel = new InfoPanel(this);
		this.add(infoPanel);
		infoPanel.setLocation(0, 500);
		initSP();
		setPanel(new TestPanel(this, "GG"));
		this.setVisible(true);
	}
	
	public void setPanel(AbstractDisplayPanel panel){
		if(displayPanel != null)
			displayPanel.setVisible(false);
		this.displayPanel = panel;
		panel.setVisible(true);
		this.add(displayPanel);
	}
	
	private void initSP(){
		this.sp = new SubmitPanel(this);
		this.client = sp.getConnection();
		this.sp.setLocation(0, 0);
		this.sp.setVisible(true);
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
