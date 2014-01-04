package cocoonClient.Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class LoginPanel extends JPanel{
	private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    public LoginPanel() {
        super();
        setPreferredSize(new Dimension(800, 600));
        setSize(800, 600);
        setLayout(new GridBagLayout());
        initTextField();
        initBtn();
    }
    private void initTextField() {
    	
    	lbUsername = new JLabel("Username:");
    	GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        add(lbUsername, c);
    	tfUsername = new JTextField(20);
    	c.gridx = 4;
        c.gridy = 2;
        c.gridwidth = 7;
        c.gridheight = 1;
    	add(tfUsername, c);
	}
	private void initBtn(){
		btnCancel = new JButton("Cancel");
		GridBagConstraints c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        add(btnCancel, c);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Exit", "Really want to exit?", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
    }
    public String getUsername() {
        return tfUsername.getText().trim();
    }
 
    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
 
    public boolean isSucceeded() {
        return succeeded;
    }
    public static void main(String[] args) {
		System.out.print("1234");
    	class X extends JFrame{
			X(){
				super("Text");
				
				
				LoginPanel LP = new LoginPanel();
				
				add(LP);
				pack();
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				setVisible(true);
			}
		}
		new X();
	}
}


