package CarRacingGUI;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class JframeLauncher {
	public static void main(String[] args) {
		BufferedImage image =null ;
		try {
			image = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/GameIcon.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		JFrame frame = new JFrame("Car Racing ( Ajay )");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(380, 10);
		frame.setContentPane(new MainJPanel());
		frame.setIconImage(image);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
}
