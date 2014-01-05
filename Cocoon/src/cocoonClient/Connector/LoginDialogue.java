package cocoonClient.Connector;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginDialogue extends JDialog {
	private JLabel userLabel, passwordLabel, msg;
	private JPasswordField passwordText;
	private JButton loginButton, registerButton;
	private JTextField userText;
	public LoginDialogue(final JFrame parent){
		super(parent, "Login");
		JPanel panel = new JPanel();
		panel.setLayout(null);
		ActionListener listener = new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Authentication.authenticate(userText.getText(), passwordText.getText())){
					parent.setVisible(true);
					dispose();
				}
				else{
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							try {
								for(int i = 0; i < 2; i++){
									msg.setText("Wrong");
									Thread.sleep(250);
									msg.setText("");
									Thread.sleep(250);
								}
								
							} catch (InterruptedException e) {
								
							}
							
						}
					}).start();
					
				}
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
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(300, 160);
		this.add(panel);
		setLocation(parent.getX()+parent.getWidth()/2, parent.getY()+parent.getHeight()/2);
		this.setResizable(false);
		this.setVisible(true);
		
	}
}
