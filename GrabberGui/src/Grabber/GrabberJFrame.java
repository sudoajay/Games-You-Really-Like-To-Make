package Grabber;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GrabberJFrame {
	public static BufferedImage icon ;
	
	public static void main(String[] args) {
	try {
		icon = ImageIO.read(new File("/home/sudoajay/Documents/workspace/GrabberGui/src/Images/Icon.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	JFrame frame = new JFrame("Grabber - (Grab The Aple)" );
	frame.setLocation(300, 10);
	frame.getContentPane().add(new GrabberJPanel());
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setIconImage(icon);
	frame.setResizable(false);
	frame.pack();
	frame.setVisible(true);
	
}
	
}
