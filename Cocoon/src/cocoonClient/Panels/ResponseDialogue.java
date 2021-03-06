package cocoonClient.Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ResponseDialogue extends JDialog{
	
	private String response;
	public ResponseDialogue(JFrame parent, String response){
		super(parent, "Response");
		this.response = response;
		this.setAlwaysOnTop(true);
		this.setLocation(parent.getX()+parent.getWidth()/2+100, parent.getY()+parent.getHeight()/2);
		this.setPreferredSize(new Dimension(600, 300));
		this.setSize(600, 300);
		this.setResizable(false);
		this.setVisible(true);
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
				dispose();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		init();
	}

	private void init() {
		SwingUtilities.invokeLater(new Runnable() {
	          @Override
	          public void run() {
	        	  JPanel panel = new JPanel();
	      		panel.setVisible(true);
	      		panel.setLayout(new BorderLayout());
	      		JTextArea textArea = new JTextArea (10,15);
	      		textArea.setFont(new Font("Courier New", Font.PLAIN, 16));
	      		textArea.setEditable(false);
	      		textArea.setBackground(new Color(0, 0, 0, 0));
	      		JScrollPane pane = new JScrollPane(textArea);
	      		panel.add(pane, BorderLayout.NORTH);
	      		JButton btn = new JButton("OK");
	      		btn.addActionListener(new ActionListener() {
	      			
	      			@Override
	      			public void actionPerformed(ActionEvent e) {
	      				dispose();
	      			}
	      		});
	      		JPanel btnpanel = new JPanel();
	      		btnpanel.add(btn);
	      		panel.add(btnpanel, BorderLayout.SOUTH);
	      		add(panel);
	      		textArea.setText(response);
	          }
	        });
		
	}
}
