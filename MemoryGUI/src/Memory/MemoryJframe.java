package Memory;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MemoryJframe {
	public static void main(String[] args) {
		BufferedImage imageIcon = null;
		try {
			imageIcon = ImageIO.read(new File("/home/sudoajay/Documents/workspace/MemoryGUI/src/Images/MemoryIcon.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame("Memory ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 100);
		frame.setIconImage(imageIcon);
		frame.getContentPane().add(new MemoryJPanel());
		frame.pack();
		frame.setVisible(true);
	}
}
