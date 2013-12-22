package cocoonClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private InfoPanel infoPanel;
	private ChatClient c;
	private SubmitPanel sp;
	public MainFrame() {
		this.setTitle("Cocoon");
		this.setSize(800, 600);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocation(80,60);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMenu();
		infoPanel = new InfoPanel();
		this.add(infoPanel);
		infoPanel.setLocation(0, 500);initSP();
		this.setVisible(true);
	}
	private void initSP(){
		this.sp = new SubmitPanel();
		this.c = sp.c;
		this.add(sp);
		this.sp.setLocation(50, 50);
		this.sp.setVisible(true);
	}
	private void setMenu(){
		 JMenuBar jmb = new JMenuBar();
			setJMenuBar(jmb);
			jmb.setOpaque(true);
			JMenu jms = new JMenu("Set(S)");
			jms.setMnemonic('S');
			JMenu jmc = new JMenu("Connect(C)");
			
			JMenu change = new JMenu("Change IP");
			
			JMenuItem ip0 = new JMenuItem("127.0.0.1:8000");
			ip0.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					c.setIPAddress("127.0.0.1");
					c.setPort(8000);
					c.connect();
					if(c.isConnected())
						JOptionPane.showMessageDialog(null ,"Connectted Successfully!!"); 
					else
						JOptionPane.showMessageDialog(null, "Not Connected!!"); 
				}
			});
			change.add(ip0);
			
			JMenuItem ip1 = new JMenuItem("140.114.200.157:8000");
			ip1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					c.setIPAddress("140.114.200.157");
					c.setPort(8000);
					c.connect();
					if(c.isConnected())
						JOptionPane.showMessageDialog(null ,"Connectted Successfully!!"); 
					else
						JOptionPane.showMessageDialog(null, "Not Connected!!"); 
				}
			});
			change.add(ip1);
			
			JMenuItem ip2 = new JMenuItem("140.114.200.158:8000");
			ip2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					c.setIPAddress("140.114.200.158");
					c.setPort(8000);
					c.connect();
					if(c.isConnected())
						JOptionPane.showMessageDialog(null ,"Connectted Successfully!!"); 
					else
						JOptionPane.showMessageDialog(null, "Not Connected!!"); 
				}
			});
			change.add(ip2);
			
			JMenuItem ip3 = new JMenuItem("140.114.200.159:8000");
			ip3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					c.setIPAddress("140.114.200.159");
					c.setPort(8000);
					c.connect();
					if(c.isConnected())
						JOptionPane.showMessageDialog(null ,"Connectted Successfully!!"); 
					else
						JOptionPane.showMessageDialog(null, "Not Connected!!"); 
				}
			});
			change.add(ip3);
			
			JMenuItem ip4 = new JMenuItem("140.114.200.160:8000");
			ip4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					c.setIPAddress("140.114.200.160");
					c.setPort(8000);
					c.connect();
					if(c.isConnected())
						JOptionPane.showMessageDialog(null ,"Connectted Successfully!!"); 
					else
						JOptionPane.showMessageDialog(null, "Not Connected!!"); 
				}
			});
			change.add(ip4);
			
			
			
			JMenuItem connect = new JMenuItem("Reconnect(C)", KeyEvent.VK_C);
			connect.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					c.connect();
					if(c.isConnected())
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
