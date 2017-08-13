package Pong;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class PongJframe  extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static PongJPanel panel = new PongJPanel();
	public static void main(String[] args) {
		BufferedImage icon = null ; 
		
		try {
			icon = ImageIO.read(new File("/home/sudoajay/Documents/workspace/PongGui/src/Images/GameIcon.png"));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		JFrame frame = new JFrame(" Ping Pong ") ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 100);
		frame.setResizable(false);
		frame.getContentPane().add (panel);
		frame.setIconImage(icon);
		frame.pack();
		frame.setVisible(true);
	}
	
}
