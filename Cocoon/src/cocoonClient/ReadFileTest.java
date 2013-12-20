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


public class ReadFileTest extends JFrame{
	ChatClient c;
	JSONObject json;
	JTextArea t;
	JScrollPane scroll;
	ReadFileTest(){
		super();
		c = new ChatClient("140.114.200.158", 8000);
		c.connect();
		setLocation(new Point(500, 300));
		setSize(500, 500);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMenu();
		setTextArea();
	    setBtn();
		setVisible(true);
		
	}
	private void setTextArea(){
		t = new JTextArea();
		t.setPreferredSize(new Dimension(450, 400));
		t.setSize(t.getPreferredSize());
		//add(t);
		t = new JTextArea (20, 40);
	    t.setEditable (false); // set textArea non-editable
	    scroll = new JScrollPane (t);
	    scroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    //Add Textarea in to middle panel
	    add(scroll);
	    /*setHighLighter();
	    add(highlighter);*/
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
	private void setMenu(){
		 JMenuBar jmb = new JMenuBar();
			setJMenuBar(jmb);
			jmb.setOpaque(true);
			JMenu jms = new JMenu("�]�w(S)");
			jms.setMnemonic('S');
			JMenu jmc = new JMenu("�s��(C)");
			
			JMenu change = new JMenu("����IP");
			
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
			
			
			
			JMenuItem connect = new JMenuItem("���s�s��(C)", KeyEvent.VK_C);
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

	/*void setHighLighter(){
		SyntaxHighlighterParser parser = new SyntaxHighlighterParser(new BrushXml());
        // turn HTML script on
        parser.setHtmlScript(true);
        // set HTML Script brushes
        parser.setHTMLScriptBrushes(Arrays.asList(new BrushCss(), new BrushJScript()));
        // besides set, you can also add
        parser.addHTMLScriptBrush(new BrushPhp());

        // use XML (for HTML) brush and RDark theme
        highlighter = new SyntaxHighlighter(new SyntaxHighlighterParser(new Brush()), new ThemeRDark());
        highlighter.setFirstLine(0);
        highlighter.setPreferredSize(new Dimension(450, 400));
		highlighter.setSize(highlighter.preferredSize());
	
	          // set the content of the script, the example.html is located in the jar: /syntaxhighlighter/example/example.html
	    highlighter.setContent(" ");
	     
	        
	}*/
	public static void main(String[] args) throws Exception {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		new ReadFileTest();		
		
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
			//System.setOut(new PrintStream(new File("out.txt")));
			//System.out.println(code);
			//System.out.println("1234");
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

