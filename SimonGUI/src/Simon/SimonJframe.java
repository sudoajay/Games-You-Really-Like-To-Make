package Simon;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SimonJframe    {
public static void main(String[] args) {
	BufferedImage image = null ;
	 try {
		image = ImageIO.read(new File("/home/sudoajay/Documents/workspace/SimonGUI/src/Images/Icon.png"));
	} catch (Exception e) {
	e.printStackTrace();
	}
	
	JFrame frame = new  JFrame("Simon - Not Easy to Play ! ");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocation(300, 20);
	frame.setContentPane(new SimonJPanel());
	frame.pack();
	frame.setIconImage(image);
	frame.setResizable(false);
	frame.setVisible(true);
	
}
}
