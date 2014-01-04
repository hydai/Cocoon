package cocoonClient;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class CocoonButton extends JButton{
	CocoonButton(){
		super();
		ImageIcon initialIcon = new ImageIcon("res/Button.png");
		ImageIcon pressedIcon = new ImageIcon("res/ButtonDown.png");
		Image initImg = initialIcon.getImage().getScaledInstance(160 ,68, Image.SCALE_DEFAULT);
		Image pressedImg = pressedIcon.getImage().getScaledInstance(160 ,68, Image.SCALE_DEFAULT);
		this.setPressedIcon(new ImageIcon(pressedImg));
		this.setIcon(new ImageIcon(initImg));
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setHorizontalTextPosition(JButton.CENTER);
	}
	CocoonButton(String title){
		this();
		this.setText(title);
		
	}
}
