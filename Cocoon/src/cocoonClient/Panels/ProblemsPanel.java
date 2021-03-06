package cocoonClient.Panels;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import JSONTransmitProtocol.newcreater.JSONCreater;
import JSONTransmitProtocol.newcreater.query.CreaterQuery;
import JSONTransmitProtocol.newcreater.query.QueryQuestion;
import cocoonClient.Component.*;
import cocoonClient.Data.GetProblemSet;
import cocoonClient.Data.UserInfo;
import cocoonClient.Frame.SubmitFrame;

public class ProblemsPanel extends CocoonDisplayPanel{
	private JTree tree;
	private Browser browser;
	private HashMap<String, Integer> problemSet;
	private SubmitFrame submitFrame;
	public ProblemsPanel(){
		super(UserInfo.getMainFrame());
		GetProblemSet set = new GetProblemSet();
		problemSet = set.getProblemSet();
		UserInfo.setProblemSet(set);
		this.setLayout(new BorderLayout());
		this.submitFrame = new SubmitFrame();
		initBrowser();
		initTree();
		initButtonPanel();
	}
	private void initButtonPanel() {
		CocoonRightPanel panel = new ProblemsRightPanel(submitFrame);
		this.setRightPanel(panel);
	}
	private void initBrowser(){
		browser = new Browser();
		browser.loadURL("https://dl.dropboxusercontent.com/u/113630504/OJ/welcome.htm");
		this.add(browser, BorderLayout.CENTER);
	}
	 private void initTree() {
		//create the root node  
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
		//create the child nodes
	    DefaultMutableTreeNode basic = new DefaultMutableTreeNode("Basic");
	    Iterator iter = problemSet.entrySet().iterator(); 
	    while (iter.hasNext()) { 
	        Map.Entry entry = (Map.Entry) iter.next(); 
	        String key = (String) entry.getKey(); 
	       basic.add(new DefaultMutableTreeNode(key));
	    } 
	    /*DefaultMutableTreeNode intermediate = new DefaultMutableTreeNode("Intermediate");
	    intermediate.add(new DefaultMutableTreeNode("empty"));
	    DefaultMutableTreeNode hard = new DefaultMutableTreeNode("Hard");
	    hard.add(new DefaultMutableTreeNode("empty"));*/
	    //create the tree by passing in the root node
	    root.add(basic);
	    /*root.add(intermediate);
	    root.add(hard);*/
	    tree = new JTree(root);
	    tree.setAutoscrolls(true);
	    tree.setFont(new Font("", Font.PLAIN, 10));
	    tree.setRootVisible(false);
	    tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				String selection;
				selection = tree.getLastSelectedPathComponent().toString();
				if(problemSet.containsKey(selection)){
					((SubmitPanel)UserInfo.getPanels().get("SubmissionResponse")).setSubmitable(true);
					UserInfo.setProblemName(selection);
					UserInfo.setPID(problemSet.get(selection));
					submitFrame.changeProblemName(selection);
					String url = "http://m101.nthu.edu.tw/~s101062124/" + problemSet.get(selection)+ ".html";
					browser.loadURL(url);
					
					if(UserInfo.getPID() > 0){
						if(UserInfo.getUID() <= 0)
							 return;
						 JSONCreater json = new JSONCreater("query")
						 .setInfo(UserInfo.getUserInfo())
						 .setQuery(new CreaterQuery("question", new QueryQuestion("problemrate", UserInfo.getPID())));
						 UserInfo.getClient().sendMessage(json.toString());
					}
					
				}
				else{
					submitFrame.changeProblemName("");
					((SubmitPanel)UserInfo.getPanels().get("SubmissionResponse")).setSubmitable(false);
				}
			}
		});
	    tree.setOpaque(false);
	    tree.setPreferredSize(new Dimension(160, 500));
	    this.add(tree, BorderLayout.WEST);
	 }
	
}
