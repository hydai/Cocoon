package cocoonClient.Panels;
import javax.swing.*;	
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;

import org.json.JSONObject;

import cocoonClient.Connector.AbstractConnector;
import cocoonClient.Connector.ChatClient;
import cocoonClient.Data.UserInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;	 
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;

import JSONTransmitProtocol.newReader.JSONReader;
import JSONTransmitProtocol.newcreater.*;
import JSONTransmitProtocol.newcreater.info.CreaterInfo;
import JSONTransmitProtocol.newcreater.submission.CreaterSubmission;
import JSONTransmitProtocol.newcreater.submission.SubmissionSent;



public class SubmitPanel extends JPanel implements AbstractConnector{
	private ChatClient client;
	private JSONObject json;
	private JTextArea t;
	private JScrollPane scroll;
	private String selectPath = null, language = "CPP";
	private JButton btn, btn2;
	private boolean isSubmittable;
	private JFrame parent;
	private JLabel msg;
	public SubmitPanel(JFrame parent){
		this.parent = parent;
		//By default, connect to localhost.
		client = UserInfo.getClient();
		client.connect();
		UserInfo.getPanels().put("SubmissionResponse", this);
		setLayout(new BorderLayout());
		setRadioButton();
		setTextArea();
	    setBtn();
		setVisible(true);
	}
	
	public void setSubmitable(boolean bool){
		btn2.setEnabled(bool);
	}
	
	private void setRadioButton(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		                               
		msg = new JLabel("                                                                              ");
		msg.setForeground(Color.blue);
		panel.add(msg);
		panel.add(new JLabel("Language: "));
		ButtonGroup bg = new ButtonGroup();
		JRadioButton radioButtonC = new JRadioButton("C");
		bg.add(radioButtonC);
		radioButtonC.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				language = "C";
			}
		});
		
		JRadioButton radioButtonCPlusPlus = new JRadioButton("C++");
		bg.add(radioButtonCPlusPlus);
		radioButtonCPlusPlus.setSelected(true); //By default, choose C++
		radioButtonCPlusPlus.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				language = "CPP";
			}
		});
		panel.add(radioButtonC);
		panel.add(radioButtonCPlusPlus);
		this.add(panel, BorderLayout.NORTH);
	}
	private void setTextArea(){
		isSubmittable = false;
		t =  new JTextArea (20,30);
		t.setFont(new Font("Courier New", Font.PLAIN, 16));
		t.setText("");
		t.setPreferredSize(new Dimension(500, 500));
		t.setSize(t.getPreferredSize());
		
		//t.setFont(new Font("微軟正黑體", Font.BOLD, 14));
	    t.setEditable (true); // set textArea non-editable
	    t.setTabSize(2);
	    t.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				isSubmittable = true;
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
	    scroll = new JScrollPane (t);
	    scroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    //Add Textarea in to middle panel
	    this.add(scroll, BorderLayout.CENTER);
	}
	private void setBtn(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		btn = new JButton("Choose");
		panel.add(btn);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choose();
			}
		});
		btn2 = new JButton("Submit");
		panel.add(btn2);
		btn2.setEnabled(false);
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(isSubmittable);
				if(client.isConnected()){
					if(!isSubmittable){
						JOptionPane.showMessageDialog(parent, "Please Select a File To Submit!!"); 
					}		
					else{
						if(t.getText().length() > 10240){
							JOptionPane.showMessageDialog(parent, "Please submit file which is less than 10 KB");
						}
						else{
							submit();
							//JOptionPane.showMessageDialog(parent, "Submitted Successfully!!"); 
							new Thread(new Runnable(){
								@Override
								public void run() {
									btn2.setEnabled(false);
									msg.setText("Submitted Successfully!!                                       ");
									for(int i = 3; i > 0; i--){
										btn2.setText("Submit (" + i + ")");
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}	
									}
									msg.setText("                                                                              ");
									btn2.setText("Submit");
									btn2.setEnabled(true);
								}
								
							}).start();;
						}
					}
					
				}
				else
					JOptionPane.showMessageDialog(parent, "Not Connected!!"); 
			}
		});
		this.add(panel, BorderLayout.SOUTH);
	}
	private void choose(){
		String fileName = null;
		try { 
			JFileChooser chooser;
			if(selectPath == null)
				chooser = new JFileChooser();
			else
				chooser = new JFileChooser(selectPath);
			int ret=chooser.showOpenDialog(parent);
			if(ret==JFileChooser.APPROVE_OPTION){
				fileName = chooser.getSelectedFile().getPath();
				System.out.println("Choose: "+ fileName);
			}
			selectPath = fileName;
			StringBuffer code = new StringBuffer("");
			String line;
			
			//Prevent user from submitting large file.
			File file = new File(fileName);
			System.out.println(file.length());
			if(file.length() > 10240L){
				
				JOptionPane.showMessageDialog(parent, "Please submit file which is less than 10 KB");
				return;
			}
			
			FileInputStream fis = new FileInputStream(fileName); 
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr); 
			while ((line = br.readLine()) != null)
				code.append(line + "\n");
			System.out.println("code:\n" + code);
			t.setText(code.toString());
			isSubmittable = true;
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	private void submit(){
		if(isSubmittable){	
			json = new JSONCreater("submission")
			.setInfo(UserInfo.getUserInfo())
			.setSubmission(new CreaterSubmission("sent", new SubmissionSent(t.getText(), language)));
			client.sendMessage(json.toString());
			t.setText("");
			json = null;
			isSubmittable = false;
		}
	}
	public ChatClient getConnection(){
		return client;
	}
	
	@Override
	public void recieveResponse(String response){
		UserInfo.getPanels().get("Status").recieveResponse(response);
		JSONReader reader = new JSONReader(response);
		if(reader.getSubmission().getUID() == UserInfo.getUID()){
			String result = "User: " 
		+ reader.getSubmission().getUsername() + "\nResponse: " 
		+ reader.getSubmission().getResult();   
			new ResponseDialogue(parent, result);
			
			
		}
	}
}	

