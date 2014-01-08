package cocoonClient.Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.*;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;

import JSONTransmitProtocol.newReader.JSONReader;
import JSONTransmitProtocol.newcreater.JSONCreater;
import JSONTransmitProtocol.newcreater.query.CreaterQuery;
import JSONTransmitProtocol.newcreater.query.QueryQuestion;
import cocoonClient.Connector.AbstractConnector;
import cocoonClient.Data.UserInfo;

public class InfoRightPanel extends CocoonRightPanel implements AbstractConnector{
	private JTable table;
	private DefaultTableModel dtm;
	private List<String> friendlist;
	public InfoRightPanel(){
		super(UserInfo.getMainFrame());
		UserInfo.getPanels().put("friendlist", this);
		this.setLayout(new BorderLayout());
		initTable();
	}
	
	private void initTable() {
		table = new JTable(){
	        public void valueChanged(ListSelectionEvent e){
	          super.valueChanged(e); //呼叫基礎類別的valueChanged()方法, 否則選取動作無法正常執行
	          if( table.getSelectedRow() == -1) return;//取得表格目前的選取列,若沒有選取列則終止執行方法
	        }
	      }; 
	      
	      table.setModel(new DefaultTableModel(){
	    	  @Override
	    	  public boolean isCellEditable(int row, int column){
	    		  return false;
	    	  }
	      });
	      
	      table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if ( !e.getValueIsAdjusting() ){
						 int beginIndex = e.getSource().toString().indexOf("{");
						 int endIndex = e.getSource().toString().indexOf("}");
						 String selection = e.getSource().toString().substring(beginIndex+1, endIndex);
						 String username = (String) table.getValueAt(Integer.parseInt(selection), 0);
						 JSONCreater json = new JSONCreater("query")
							.setInfo(UserInfo.getUserInfo())
							.setQuery(new CreaterQuery("question", new QueryQuestion("statistics", username)));
					//UserInfo.getClient().sendMessage(json.toString());
				    }
					
				}
			});
	      
	      table.setShowGrid(true);
	      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);         
	      table.setSelectionBackground(Color.ORANGE);//設定選取背景顏色    
	      table.setCellSelectionEnabled(true); //設定允許儲存格選取
	      table.setPreferredSize(new Dimension(175, 300));
	      table.setColumnSelectionAllowed(false);
	      dtm = (DefaultTableModel)table.getModel();
	      dtm.addColumn("Friend List");
	      table.getColumnModel().getColumn(0).setPreferredWidth(140);
	      JScrollPane pane = new JScrollPane(table);
	      pane.setPreferredSize(new Dimension(200, 300));
	      add(pane, BorderLayout.SOUTH);
	}

	@Override
	public void recieveResponse(String response) {
		JSONReader reader = new JSONReader(response);
		friendlist = reader.getQuery().getResponse().getFriendList().getFriendlist();
		int count = dtm.getRowCount();
		for(int i = count-1; i >= 0; i--)
			dtm.removeRow(i);
		for(int i = 0; i < friendlist.size(); i++){
			dtm.addRow(new String[]{friendlist.get(i)});
		}
	}
	
}
