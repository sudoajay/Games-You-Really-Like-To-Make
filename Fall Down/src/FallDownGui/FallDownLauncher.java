package FallDownGui;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class FallDownLauncher {
	public static void main(String[] args) {
		BufferedImage gameIcon = null ;
		try {
			gameIcon = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Fall Down/src/Images/GameIcon.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame("Fall Down");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 10);
		frame.getContentPane().add(new MainPanel());
		frame.pack();
		frame.setResizable(false);
		frame.setIconImage(gameIcon);
		frame.setVisible(true);
	}
}
