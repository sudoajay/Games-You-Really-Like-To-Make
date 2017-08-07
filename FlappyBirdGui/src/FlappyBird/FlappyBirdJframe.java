package FlappyBird;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class FlappyBirdJframe {
public static void main(String[] args) {
	
	BufferedImage image = null;
	try {
		image =  ImageIO.read(new File("/home/sudoajay/Documents/workspace/FlappyBirdGui/src/Images/FlappyBirdIcon.png"));
	} catch (Exception e) {
		System.out.println(e.toString());
	}
	JFrame frame = new JFrame("Flappy Bird");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setBounds(280, 80, 0,0);
	frame.setIconImage(image);
	frame.setResizable(false);
	frame.getContentPane().add(new FlappyBirdJpanel());
	frame.pack();
	frame.setVisible(true);
}
}
