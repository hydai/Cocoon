package cocoonClient;
import javax.swing.*;	

import org.json.JSONObject;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;	 //���Ѩt�ο�J�M��X�q�L�ƾڬy�A�ǦC�ƩM���t��
import java.util.Arrays;
import java.util.logging.Level;


public class SubmitPanel extends JPanel{
	ChatClient c;
	JSONObject json;
	JTextArea t;
	JScrollPane scroll;
	SubmitPanel(){
		super();
		c = new ChatClient("140.114.200.158", 8000);
		c.connect();
		setSize(400, 400);
		setLayout(new FlowLayout());
		setTextArea();
	    setBtn();
		setVisible(true);
		
	}
	private void setTextArea(){
		t = new JTextArea();
		t.setPreferredSize(new Dimension(450, 400));
		t.setSize(t.getPreferredSize());
		t = new JTextArea (20, 40);
	    t.setEditable (false); // set textArea non-editable
	    scroll = new JScrollPane (t);
	    scroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    //Add Textarea in to middle panel
	    add(scroll);
	}
	private void setBtn(){
		JButton btn = new JButton("Choose");
		add(btn);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				choose();
				
			}
		});
		JButton btn2 = new JButton("Submit");
		add(btn2);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(c.isConnected()){
					if(json == null){
						JOptionPane.showMessageDialog(null, "Please Select a File To Submit!!"); 
					}		
					else{
						submit();
						JOptionPane.showMessageDialog(null, "Submitted Successfully!!"); 
					}
					
				}
				else
					JOptionPane.showMessageDialog(null, "Not Connected!!"); 
			}
		});
		
	}
	void choose(){
		String fileName = null;
		try { 
			JFileChooser chooser = new JFileChooser();
			int ret=chooser.showOpenDialog(null);
			if(ret==JFileChooser.APPROVE_OPTION){
				fileName = chooser.getSelectedFile().getPath();
				System.out.println("�z��ܶ}�Ҧ���: "+ fileName);
			}
		
			String code = "", line;
			FileInputStream fis = new FileInputStream(fileName); 
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr); 
			while ((line = br.readLine()) != null)
				code += line + "\n";
			System.out.println("code:\n" + code);
			json = new JSONCreater("submission", "C++", code).getJSONObject();
			System.out.println(json.toString());
			code = (String) ((JSONObject)json.get("submission")).get("code");
			t.setText(code);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	void submit(){
		if(json != null){
			c.sendMessage(json.toString());
			t.setText("");
			json = null;
		}
	}
}	

