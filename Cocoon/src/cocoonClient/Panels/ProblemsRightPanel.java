package cocoonClient.Panels;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cocoonClient.Component.CocoonButton;
import cocoonClient.Connector.AbstractConnector;
import cocoonClient.Data.UserInfo;
import cocoonClient.Frame.SubmitFrame;

public class ProblemsRightPanel extends AbstractRightPanel implements AbstractConnector{
	ProblemsRightPanel(final SubmitFrame submitFrame){
		super(UserInfo.getMainFrame());
		setLayout(new FlowLayout());
		CocoonButton submit = new CocoonButton("Submit");
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				submitFrame.open();
			}
		});;
		this.add(submit);
		UserInfo.getPanels().put("problemrate", this);
	}

	@Override
	public void recieveResponse(String response) {
		
		
	}
}
