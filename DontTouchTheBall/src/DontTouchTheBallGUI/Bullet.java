package DontTouchTheBallGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	public int width = 50, height = 50 , bulletCountLimit = 50;
	public MainJPanel panel;
	public Rectangle rectBullet;
	public boolean bulletReach;

	public Bullet(MainJPanel panel) {
		this.panel = panel;
		rectBullet = new Rectangle();
		bulletReach = true;
	}

	public void CreateABullet(int x, int y) {
		rectBullet.setBounds(x + 10, y - 10, width, height);
		if(bulletCountLimit >1)
			bulletCountLimit--;
		
		panel.repaint();
	}

	public void paint(Graphics g) {
		g.setColor(Color.GREEN);

		g.drawImage(panel.bulletImage, rectBullet.x, rectBullet.y, rectBullet.width, rectBullet.height, null);
	}

	public void BulletUpdate() {
		if (rectBullet.y >= 0 - rectBullet.width) {
			rectBullet.y -= 15;
			
		} else {
			if(bulletCountLimit ==1)
				bulletCountLimit--;
			bulletReach = true;
		}
	}
}
