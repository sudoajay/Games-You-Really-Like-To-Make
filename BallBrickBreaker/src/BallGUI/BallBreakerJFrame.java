package BallGUI;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class BallBreakerJFrame {
	public static BufferedImage cursorImg ;
	public static BufferedImage image = null;
	public static Cursor blankCursor;
	public static void main(String[] args) {
		
		
		try {
			cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			 blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				    cursorImg, new Point(0, 0), "blank cursor");
			image = ImageIO.read(new File("/home/sudoajay/Documents/workspace/BallBrickBreaker/src/Images/GameIcon.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	JFrame frame = new JFrame("Ball Breaker ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BallBreakerJPanel());
		frame.setLocation(200, 10);
		frame.setIconImage(image);
		frame.getContentPane().setCursor(blankCursor);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
}
