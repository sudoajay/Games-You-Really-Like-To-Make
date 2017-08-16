package ShootForYourLifePackage;

import javax.swing.JFrame;

public class ShooterLauncher {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Shoot For Your Life - ( Ajay )");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(220, 10);
		frame.getContentPane().add(new MainPanel());
		frame.pack();
		frame.setVisible(true);

	}
}
