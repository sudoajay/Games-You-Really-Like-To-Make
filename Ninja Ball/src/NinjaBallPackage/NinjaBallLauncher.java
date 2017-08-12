 package NinjaBallPackage;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class NinjaBallLauncher {
	public static void main(String[] args) {
		BufferedImage gameIconImage = null;
		try {
			gameIconImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Ninja Ball/src/Images/GameIcon.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame("Ninja-Ball");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MainPanel());
		frame.setLocation(270, 60);
		frame.pack();
		frame.setResizable(false);
		frame.setIconImage(gameIconImage);
		frame.setVisible(true);
	}
}
