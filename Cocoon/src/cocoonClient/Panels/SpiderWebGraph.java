package cocoonClient.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;








import JSONTransmitProtocol.newReader.JSONReader;
import cocoonClient.Connector.AbstractConnector;
import cocoonClient.Data.UserInfo;

public class SpiderWebGraph extends JPanel implements AbstractConnector{
	private ChartPanel chartPanel;
	private JSONReader reader;
	public SpiderWebGraph(){
        setLayout(null);
        setPreferredSize(new Dimension(550, 500));
        UserInfo.getPanels().put("statisticsRadar", this);
	}
	public void refresh(){
		removeAll();
		chartPanel = new ChartPanel(createChart(createDataset()));
        chartPanel.setPreferredSize(new Dimension(550, 500));
        chartPanel.setBackground(new Color(0, 0, 0, 0));
        add(chartPanel);
        chartPanel.setBounds(0, 0, 550, 500);
	}
	public static JFreeChart createChart(CategoryDataset categorydataset){   
        SpiderWebPlot spiderwebplot = new SpiderWebPlot(categorydataset); 
        spiderwebplot.setBackgroundAlpha(0);
        spiderwebplot.setOutlineVisible(false);
        spiderwebplot.setLabelFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        
        JFreeChart jfreechart = new JFreeChart("Statistics", new Font(Font.SANS_SERIF, Font.BOLD, 18), spiderwebplot, false);  
        jfreechart.setBorderVisible(false);
        return jfreechart;   
    } 
	private CategoryDataset createDataset() {  
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();  
        
        Map<String, Integer> map = reader.getQuery().getResponse().getStatistics().getStatisticsMap();
        String types[] = new String[] {"DataStructure", "Array", "IO", "Math", "Sort"};
        for(String str : types){
        	defaultcategorydataset.addValue(map.get(str).doubleValue(), "", str); 
        }
        return defaultcategorydataset;   
    }
	@Override
	public void recieveResponse(String response) {
		reader = new JSONReader(response);
		refresh();
	}   
	
}
