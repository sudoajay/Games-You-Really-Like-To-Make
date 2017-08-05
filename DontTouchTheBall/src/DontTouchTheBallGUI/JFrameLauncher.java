package DontTouchTheBallGUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class JFrameLauncher {
public static void main(String[] args) {
	BufferedImage image = null;
	try {
		image= ImageIO.read(new File("/home/sudoajay/Documents/workspace/DontTouchTheBall/src/Images/GameIcon.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	JFrame frame = new  JFrame("Ajay");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(new  MainJPanel());
	frame.setLocation(30, 10);
	frame.pack();
	frame.setResizable(false);
	frame.setIconImage(image);
	frame.setVisible(true);
}
}
