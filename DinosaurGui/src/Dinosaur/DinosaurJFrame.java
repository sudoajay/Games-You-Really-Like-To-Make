package Dinosaur;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class DinosaurJFrame {

	public static void main(String[] args) {
		Image image = null;
		try {
			File file = new File("/home/sudoajay/Documents/workspace/DinosaurGui/src/Images/Icon.png");
			image = ImageIO.read(file);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		JFrame frame = new JFrame("Dinosaur Game ");
		frame.setBounds(300, 100, 0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(image);
		frame.setResizable(false);
		frame.getContentPane().add(new DinosaurJPanel());
		frame.pack();
		frame.setVisible(true);
	}

}
