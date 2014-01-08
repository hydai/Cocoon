package cocoonClient.Panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
		setLayout(null);
        setPreferredSize(new Dimension(400, 400));
        UserInfo.getPanels().put("statisticsPie", this);
        
        fxPanel = new JFXPanel();
		fxPanel.setPreferredSize(new Dimension(400, 400));
		this.add(fxPanel,BorderLayout.SOUTH);
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
	        text.setText(" Statistics of\n-" + UserInfo.getProblemName());

	        root.getChildren().add(text);
	        
	        ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("AC: " + reader.getQuery().getResponse().getProblemRate().getAccept()
	                		,reader.getQuery().getResponse().getProblemRate().getAccept()),
	                new PieChart.Data("CE: " + reader.getQuery().getResponse().getProblemRate().getCompileError()
	                		,reader.getQuery().getResponse().getProblemRate().getCompileError()),
	                new PieChart.Data("MLE: " + reader.getQuery().getResponse().getProblemRate().getMemoryLimitExceeded()
	                		,reader.getQuery().getResponse().getProblemRate().getMemoryLimitExceeded()),
	                new PieChart.Data("RE: " + reader.getQuery().getResponse().getProblemRate().getRubtimeError()
	                		,reader.getQuery().getResponse().getProblemRate().getRubtimeError()),
	                new PieChart.Data("TLE: " + reader.getQuery().getResponse().getProblemRate().getTimeLimitExceeded()
	                		,reader.getQuery().getResponse().getProblemRate().getTimeLimitExceeded()),
	                new PieChart.Data("WA: " + reader.getQuery().getResponse().getProblemRate().getWrongAnswer(),
	                		reader.getQuery().getResponse().getProblemRate().getWrongAnswer())
	                		);
	        chart = new PieChart(pieChartData);
	        
	        //chart.setTitle("Statistics");
	        chart.setTitleSide(Side.TOP);
	        chart.setMaxSize(200, 300);
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
