package DontTouchTheBallGUI;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {

	public int x, y, width = 80, height = 100;
	public MainJPanel panel;
	public Rectangle rectPlayer;

	public Player(MainJPanel panel) {
		this.panel = panel;
		rectPlayer = new Rectangle();

	}

	public void paint(Graphics g) {
		g.drawImage(panel.playerImage, rectPlayer.x, rectPlayer.y, rectPlayer.width, rectPlayer.height, null);

	}

	public void CreateAPlayer() {
		rectPlayer.setBounds(500, 545, width, height);
	}

}
