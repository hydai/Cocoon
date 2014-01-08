package cocoonClient.Panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import JSONTransmitProtocol.newReader.JSONReader;
import cocoonClient.Component.CocoonButton;
import cocoonClient.Connector.AbstractConnector;
import cocoonClient.Data.UserInfo;
import cocoonClient.Frame.SubmitFrame;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ProblemsRightPanel extends CocoonRightPanel implements AbstractConnector{
	
	ProblemsRightPanel(final SubmitFrame submitFrame){
		super(UserInfo.getMainFrame());
		setLayout(new BorderLayout());
		UserInfo.getPanels().put("problemrate", this);
		CocoonButton submit = new CocoonButton("Submit");
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				submitFrame.open();
			}
		});
		this.add(submit, BorderLayout.NORTH);
		UserInfo.getPanels().put("problemrate", this);
		fxPanel = new JFXPanel();
		fxPanel.setPreferredSize(new Dimension(200, 350));
		this.add(fxPanel,BorderLayout.SOUTH);
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
       });
	}
	final JFXPanel fxPanel;
	private  void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }
	private PieChart chart;
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
                new PieChart.Data("AC", reader.getQuery().getResponse().getProblemRate().getAccept()),
                new PieChart.Data("CE", reader.getQuery().getResponse().getProblemRate().getCompileError()),
                new PieChart.Data("MLE", reader.getQuery().getResponse().getProblemRate().getMemoryLimitExceeded()),
                new PieChart.Data("RE", reader.getQuery().getResponse().getProblemRate().getRubtimeError()),
                new PieChart.Data("TLE", reader.getQuery().getResponse().getProblemRate().getTimeLimitExceeded()),
                new PieChart.Data("WA", reader.getQuery().getResponse().getProblemRate().getWrongAnswer())
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
   private  JSONReader reader;;
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
