package cocoonClient.Connector;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.xml.stream.events.StartDocument;

import cocoonClient.Data.UserInfo;
import JSONTransmitProtocol.reader.JSONReader;

public class LoginDialogue extends JDialog implements AbstractConnector{
	private JLabel userLabel, passwordLabel, msg;
	private JPasswordField passwordText;
	private JButton loginButton, registerButton;
	private JTextField userText;
	private JFrame parent;
	public LoginDialogue(JFrame parent){
		super(parent, "Login");
		this.parent = parent;
		UserInfo.getPanels().put("login", this);
		init();
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
				System.exit(0);
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.setSize(300, 160);
		
		setLocation(parent.getX()+parent.getWidth()/2, parent.getY()+parent.getHeight()/2);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	private void init() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		ActionListener listener = new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						Authentication.authenticate(userText.getText(), passwordText.getText());	
					}
				}).start();
			}
		};
		userLabel = new JLabel("User Name");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		userText.addActionListener(listener);
		panel.add(userText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		passwordText.addActionListener(listener);
		panel.add(passwordText);

		loginButton = new JButton("Login");
		loginButton.setBounds(10, 80, 80, 25);
		loginButton.addActionListener(listener);
		panel.add(loginButton);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(180, 80, 80, 25);
		panel.add(registerButton);
		
		msg = new JLabel("",JLabel.CENTER);
		msg.setForeground(Color.RED);
		msg.setFont(new Font("", Font.BOLD, 12));
		msg.setBounds(0, 110, 300, 20);
		panel.add(msg);
		this.add(panel);
	}
	@Override
	public void recieveResponse(String response) {
		System.out.println("login:\n" + response);
		JSONReader reader = new JSONReader(response);
		if(reader.getType().equals("login")){
			System.out.println(reader.getLogin().getUid());
			if(reader.getLogin().getUid() > 0L){
				parent.setVisible(true);
                dispose();
				return;
			}
		}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					for(int i = 0; i < 2; i++){
	                    msg.setText("Incorrect User Name or Password");
	                    Thread.sleep(250);
	                    msg.setText("");
	                    Thread.sleep(250);
					}
				}
				catch(Exception e){}
				
			}
		}).start();
		
	}
}
