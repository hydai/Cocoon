package cocoonClient.Panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import JSONTransmitProtocol.reader.JSONReader;
import cocoonClient.Connector.AbstractConnector;
import cocoonClient.Data.UserInfo;

public class StatusPanel extends AbstractDisplayPanel implements AbstractConnector{
	private JTable table;
	public StatusPanel(){
		super(UserInfo.getMainFrame());
		this.setLayout(new FlowLayout());
		init();
		UserInfo.getPanels().put("status", this);
	}
	private void init() {
		try{
		      //Table//以Model物件宣告建立表格的JTable元件
		      table = new JTable(){
		        public void valueChanged(ListSelectionEvent e){
		          super.valueChanged(e); //呼叫基礎類別的valueChanged()方法, 否則選取動作無法正常執行
		          if( table.getSelectedRow() == -1) return;//取得表格目前的選取列,若沒有選取列則終止執行方法
		        }
		      };    
		      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);         
		      table.setSelectionBackground(Color.ORANGE);//設定選取背景顏色    
		      table.setCellSelectionEnabled(true); //設定允許儲存格選取
		      
		      //取得處理表格資料的Model物件,建立關聯
		      DefaultTableModel dtm = (DefaultTableModel)table.getModel(); //宣告處理表格資料的TableModel物件
		      String columnTitle[] = new String[]{"Date", "Username", "Problem", "Status", "Source"};
		      int columnWidth[] = new  int[]{80, 80, 140, 70, 70};
		      for(int i = 0; i < columnTitle.length; i++){
		    	dtm.addColumn(columnTitle[i]);
		      }
		      for(int i = 0; i < columnWidth.length; i++){
		    	//欄寬設定
			      	table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
		      }
		      //註冊回應JTable元件的MouseEvent事件的監聽器物件
		      table.addMouseListener(new MouseAdapter(){
		        public void mouseClicked(MouseEvent e){
		          int selRow  = table.rowAtPoint(e.getPoint());//取得滑鼠點選位置所在之資料的列索引
		          String Size = (String) table.getValueAt(selRow, 2);  //取得被點選資料列的第3欄的值
		          if (Integer.parseInt(Size)> 0 ){
		           
		          }
		        }
		      });
		      
		    }catch ( Exception  e){
		      e.printStackTrace();
		    }
		Box bxMix = new Box(BoxLayout.X_AXIS);
		bxMix.add(Box.createHorizontalStrut(10));//Strut:固定長度的透明支架
		bxMix.add(new JScrollPane(table));//放在 JScrollPane 內才有, Scrollbar
		bxMix.add(Box.createHorizontalStrut(10));//Strut:固定長度的透明支架
		bxMix.add(Box.createVerticalStrut(10));//Strut:固定長度的透明支架
		add(bxMix);
	}
	
	private void addStatus(String response){
		JSONReader reader = new JSONReader(response);
	    DefaultTableModel dtm = (DefaultTableModel)table.getModel();
	    dtm.addRow(new String[] {
	    		reader.getBroadcast().getStatus().getTime(),
	    		reader.getBroadcast().getStatus().getUID().toString(),
	    		"Name",
	    		reader.getBroadcast().getStatus().getResult(),
	    		reader.getSubmission().getLanguage(),
	    		});  
	}
	
	@Override
	public void recieveResponse(String response) {
		addStatus(response);
	}
}
