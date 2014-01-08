package cocoonClient.Panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.swing.JPanel;

import cocoonClient.Connector.AbstractConnector;
import cocoonClient.Data.UserInfo;
import JSONTransmitProtocol.newReader.JSONReader;

public class PieGraph extends JPanel implements AbstractConnector{
	private final JFXPanel fxPanel;
	private PieChart chart;
	private JSONReader reader;
	public PieGraph(){
		setLayout(new BorderLayout());
       
        UserInfo.getPanels().put("statisticsPie", this);
        
        fxPanel = new JFXPanel();
		fxPanel.setPreferredSize(new Dimension(550, 500));
		this.add(fxPanel,BorderLayout.CENTER);
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
       });
	}
	private  void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }
	 private Scene createScene() {
	        Group  root  =  new  Group();
	        Scene  scene  =  new  Scene(root, Color.TRANSPARENT);
	        if(reader == null)
	        	return scene;
	        //
	        Text  text  =  new  Text();
	        
	        text.setX(30);
	        text.setY(20);
	        text.setFont(new Font(14));
	        //text.setTextAlignment(TextAlignment.CENTER);
	        //text.setText("Distribution");

	        root.getChildren().add(text);
	        Map<String, Integer> map = reader.getQuery().getResponse().getStatistics().getStatisticsMap();
	        String type[] = new String[] {"RuntimeError", "TimeLimitExceeded", "CompileError", "WrongAnswer", "MemoryLimitExceeded"
	        		,"Accept"};
	        ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                	new PieChart.Data("RE: " + map.get(type[0]), map.get(type[0])),
	                	new PieChart.Data("TLE: " + map.get(type[1]), map.get(type[1])),
	                	new PieChart.Data("CE: " + map.get(type[2]), map.get(type[2])),
	                	new PieChart.Data("WA: " + map.get(type[3]), map.get(type[3])),
	                	new PieChart.Data("MLE: " + map.get(type[4]), map.get(type[4])),
	                	new PieChart.Data("AC: " + map.get(type[5]), map.get(type[5]))
	                		);
	        chart = new PieChart(pieChartData);
	        
	        //chart.setTitle("Statistics");
	        chart.setTitleSide(Side.TOP);
	        chart.setMaxSize(500, 500);
	        chart.setLegendVisible(true);
	        chart.setLegendSide(Side.BOTTOM);
	        ((Group) scene.getRoot()).getChildren().add(chart);
	        
	        //
	        return (scene);
	    }
	@Override
	public void recieveResponse(String response) {
		reader = new JSONReader(response);
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
       });
		
	}
}
