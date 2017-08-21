package Snake;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SnakeJframe {
	public static void main(String[] args) {
		BufferedImage image = null ;
		try {
			image= ImageIO.read(new File("/home/sudoajay/Documents/workspace/SnakeGui/src/Images/GameIcon.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFrame frame =  new JFrame("Snake - (Old Memory Game)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(image);
		frame.setLocation(280, 80);
		frame.getContentPane().add(new SnakeJPanel());
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		
		
	}
}
