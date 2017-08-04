package MoveForwardPackage;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MoveForwardLauncher {
	public static void main(String[] args) {
		BufferedImage gameIcon = null; 
				 try {
					gameIcon = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Move Forward/src/Images/GameIcon.png"));
				} catch (Exception e) {
					// TODO: handle exception
				}
		
		JFrame frame = new JFrame("Move - Forward (Ajay)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MainPanel());
		frame.setLocation(30, 10);
		frame.setIconImage(gameIcon);
		frame.pack();
		frame.setVisible(true);
	}
}
