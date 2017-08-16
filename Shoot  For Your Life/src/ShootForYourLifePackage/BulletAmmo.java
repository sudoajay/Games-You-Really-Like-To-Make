package ShootForYourLifePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BulletAmmo {
	public MainPanel panel;
	public Player player;
	public Rectangle bulletAmmoRect;
	public int size = 10;
	public Random random = new Random();

	public BulletAmmo(MainPanel panel, Player player) {
		this.panel = panel;
		this.player = player;
		bulletAmmoRect = new Rectangle();

	}
	public void ClearAll() {
		bulletAmmoRect.setBounds(-100 , -100, size, size);
	}
	public void CreateAnBulletAmmoBound(int no) {
		if(no == 1)
		bulletAmmoRect.setBounds(random.nextInt(1000) + 1, random.nextInt(700) + 1, size, size);
		else {
			bulletAmmoRect.setBounds(-100 , -100, size, size);

		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(bulletAmmoRect.x, bulletAmmoRect.y, bulletAmmoRect.width, bulletAmmoRect.height);
	}

	public void AmmoUpdate() {
		if (bulletAmmoRect.intersects(player.playerRect)) {
			panel.progress = false;
			panel.progressBar.setVisible(false);
			panel.bulletLeft += (20-panel.count)*2;
			CreateAnBulletAmmoBound(2);
		}
	}
}
