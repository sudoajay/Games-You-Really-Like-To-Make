package MemoryGUI;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MemoryJframe {
	public static void main(String[] args) {
		BufferedImage  imageicon = null;
		try {
			
		
		imageicon = ImageIO.read(new File("/home/sudoajay/Documents/workspace/NextLevelMemory/src/Images/Icon.png"));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		JFrame frame = new JFrame("New Version Memory ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(imageicon);
		frame.getContentPane().add(new  MemoryJPanel());
		frame.setLocation(300, 100);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		
		
	}
}
