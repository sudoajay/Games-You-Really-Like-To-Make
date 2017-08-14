package Puzzle;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Puzzleframe {
	public static void main(String[] args) {
		BufferedImage icon = null ; 
		try {
			icon = ImageIO.read(new File("/home/sudoajay/Documents/workspace/PuzzleGUI/src/Images/puzzleIcon.png"));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		JFrame frame = new  JFrame("Puzzle That Confuse You !");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setIconImage(icon);
		frame.getContentPane().add(new PuzzlePanel());
		frame.setLocation(30, 50);
		frame.setResizable(true);
		frame.pack();
		frame.setVisible(true);
	}
}
