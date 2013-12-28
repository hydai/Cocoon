package cocoonClient;
import javax.swing.*;	
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONObject;

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
import java.io.*;	 //���Ѩt�ο�J�M��X�q�L�ƾڬy�A�ǦC�ƩM���t��
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;
import JSONTransmitProtocol.creater.*;


public class SubmitPanel extends AbstractDisplayPanel{
	private ChatClient client;
	private JSONObject json;
	private JTextArea t;
	private JScrollPane scroll;
	private String selectPath = null, language = "C++";
	private JButton btn, btn2;
	private boolean isSubmittable;
	SubmitPanel(MainFrame parent){
		super(parent);
		//By default, connect to localhost.
		client = UserInfo.getClient();
		client.connect();
		setLayout(new FlowLayout());
		setRadioButton();
		setTextArea();
	    setBtn();
		setVisible(true);
	}
	private void setRadioButton(){
		add(new JLabel("Language: "));
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
				language = "C++";
			}
		});
		add(radioButtonC);
		add(radioButtonCPlusPlus);
	}
	private void setTextArea(){
		isSubmittable = false;
		t = new JTextArea();
		t.setText("");
		t.setPreferredSize(new Dimension(500, 500));
		t.setSize(t.getPreferredSize());
		t = new JTextArea (20,40);
		t.setFont(new Font("微軟正黑體", Font.BOLD, 14));
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
	    add(scroll);
	}
	private void setBtn(){
		btn = new JButton("Choose");
		add(btn);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choose();
			}
		});
		btn2 = new JButton("Submit");
		add(btn2);
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
							JOptionPane.showMessageDialog(parent, "Submitted Successfully!!"); 
							new Thread(new Runnable(){
								@Override
								public void run() {
									btn2.setEnabled(false);
									for(int i = 3; i > 0; i--){
										btn2.setText("Submit (" + i + ")");
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}	
									}
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
			String time = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
			json = new JSONCreater().setType("submission")
			.setSubmission(t.getText(), language)
			.setSubmissionInfo(UserInfo.getUID(), UserInfo.getIP(), time);
			System.out.println(json.toString());
			client.sendMessage(json.toString());
			t.setText("");
			json = null;
			isSubmittable = false;
		}
	}
	public ChatClient getConnection(){
		return client;
	}
}	

