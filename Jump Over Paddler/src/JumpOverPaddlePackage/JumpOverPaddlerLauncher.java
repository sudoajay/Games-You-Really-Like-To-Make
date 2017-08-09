package JumpOverPaddlePackage;

import javax.swing.JFrame;

public class JumpOverPaddlerLauncher {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Jump-It (Ajay)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MainPanel() );
		frame.pack();
		frame.setLocation(200, 50);
		frame.setVisible(true);
	}
}
