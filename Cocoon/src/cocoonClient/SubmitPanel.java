package cocoonClient;
import javax.swing.*;	

import org.json.JSONObject;




import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
	String selectPath = null;
	JButton btn, btn2;
	boolean isSubmittable;
	SubmitPanel(){
		super();
		c = new ChatClient("140.114.200.158", 8000);
		c.connect();
		setSize(500, 500);
		setLayout(new FlowLayout());
		setTextArea();
	    setBtn();
		setVisible(true);
		
	}
	private void setTextArea(){
		isSubmittable = false;
		t = new JTextArea();
		t.setText("");
		t.setPreferredSize(new Dimension(500, 500));
		t.setSize(t.getPreferredSize());
		t = new JTextArea (20,35);
		t.setFont(new Font("微軟正黑體", Font.BOLD, 12));
	    t.setEditable (true); // set textArea non-editable
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
				if(c.isConnected()){
					if(!isSubmittable){
						JOptionPane.showMessageDialog(null, "Please Select a File To Submit!!"); 
					}		
					else{
						submit();
						JOptionPane.showMessageDialog(null, "Submitted Successfully!!"); 
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
				else
					JOptionPane.showMessageDialog(null, "Not Connected!!"); 
			}
		});
		
	}
	void choose(){
		String fileName = null;
		try { 
			JFileChooser chooser;
			if(selectPath == null)
				chooser = new JFileChooser();
			else
				chooser = new JFileChooser(selectPath);
			int ret=chooser.showOpenDialog(null);
			if(ret==JFileChooser.APPROVE_OPTION){
				fileName = chooser.getSelectedFile().getPath();
				System.out.println("Choose: "+ fileName);
			}
			selectPath = fileName;
			String code = "", line;
			FileInputStream fis = new FileInputStream(fileName); 
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr); 
			while ((line = br.readLine()) != null)
				code += line + "\n";
			System.out.println("code:\n" + code);
			t.setText(code);
			isSubmittable = true;
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	void submit(){
		if(isSubmittable){
			json = new JSONCreater("submission", "C++", t.getText()).getJSONObject();
			System.out.println(json.toString());
			c.sendMessage(json.toString());
			t.setText("");
			json = null;
			isSubmittable = false;
		}
	}
}	

