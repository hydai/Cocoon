package cocoonClient.Panels;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import cocoonClient.Component.*;
import cocoonClient.Data.GetProblemSet;
import cocoonClient.Data.UserInfo;
import cocoonClient.Frame.SubmitFrame;

public class ProblemsPanel extends AbstractDisplayPanel{
	private JTree tree;
	private Browser browser;
	private HashMap<String, Integer> problemSet;
	private SubmitFrame submitFrame;
	public ProblemsPanel(){
		super(UserInfo.getMainFrame());
		problemSet = new GetProblemSet().getProblemSet();
		this.setLayout(new BorderLayout());
		this.submitFrame = new SubmitFrame();
		initBrowser();
		initTree();
		initButtonPanel();
	}
	private void initButtonPanel() {
		AbstractRightPanel panel = new ProblemsRightPanel(submitFrame);
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
	    DefaultMutableTreeNode intermediate = new DefaultMutableTreeNode("Intermediate");
	    intermediate.add(new DefaultMutableTreeNode("empty"));
	    DefaultMutableTreeNode hard = new DefaultMutableTreeNode("Hard");
	    hard.add(new DefaultMutableTreeNode("empty"));
	    //create the tree by passing in the root node
	    root.add(basic);
	    root.add(intermediate);
	    root.add(hard);
	    tree = new JTree(root);
	    tree.setRootVisible(false);
	    tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				String selection;
				selection = tree.getLastSelectedPathComponent().toString();
				if(problemSet.containsKey(selection)){
					UserInfo.setPID(problemSet.get(selection));
					submitFrame.changeProblemName(selection);
					String url = "https://dl.dropboxusercontent.com/u/176423666/OJ/" + problemSet.get(selection)+ ".html";
					browser.loadURL(url);
				}
			}
		});
	    tree.setOpaque(false);
	    tree.setPreferredSize(new Dimension(160, 500));
	    this.add(tree, BorderLayout.WEST);
	 }
}
