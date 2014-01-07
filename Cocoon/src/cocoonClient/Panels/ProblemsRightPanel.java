package cocoonClient.Panels;

import java.awt.BorderLayout;
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
import JSONTransmitProtocol.newReader.JSONReader;
import cocoonClient.Component.CocoonButton;
import cocoonClient.Connector.AbstractConnector;
import cocoonClient.Data.UserInfo;
import cocoonClient.Frame.SubmitFrame;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ProblemsRightPanel extends AbstractRightPanel implements AbstractConnector{
	
	ProblemsRightPanel(final SubmitFrame submitFrame){
		super(UserInfo.getMainFrame());
		setLayout(new BorderLayout());
		CocoonButton submit = new CocoonButton("Submit");
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				submitFrame.open();
			}
		});
		this.add(submit, BorderLayout.NORTH);
		UserInfo.getPanels().put("problemrate", this);
		final JFXPanel fxPanel = new JFXPanel();
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
	private PieChart chart;
    private Scene createScene() {
        Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root, Color.ALICEBLUE);
        
        //
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));
        
        chart = new PieChart(pieChartData);
        chart.setScaleX(0.5);
        chart.setScaleY(0.5);
        chart.setTitle("Statistics");

        ((Group) scene.getRoot()).getChildren().add(chart);
        
        //
        return (scene);
    }
    
	@Override
	public void recieveResponse(String response) {
		JSONReader reader = new JSONReader(response);
		/*Map<String, Integer> map = ;
		"TotalSubmission":<int>
		"Accept":<int>
		"WrongAnswer":<int>
		"RuntimeError":<int>
		"TimeLimitExceeded":<int>
		"MemoryLimitExceeded":<int>
		"CompileError":<int>*/
		ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("AC", reader.getQuery().getResponse().getProblemRate().getAccept()),
                new PieChart.Data("CE", reader.getQuery().getResponse().getProblemRate().getCompileError()),
                new PieChart.Data("MLE", reader.getQuery().getResponse().getProblemRate().getMemoryLimitExceeded()),
                new PieChart.Data("RE", reader.getQuery().getResponse().getProblemRate().getRubtimeError()),
                new PieChart.Data("TLE", reader.getQuery().getResponse().getProblemRate().getTimeLimitExceeded()),
                new PieChart.Data("WA", reader.getQuery().getResponse().getProblemRate().getWrongAnswer())
                		);
        chart.setAnimated(true);
        
        chart = new PieChart(pieChartData);
        chart.setScaleX(0.4);
        chart.setScaleY(0.4);
		chart.setTitle("Statistics");
	}
}
