package ShootForYourLifePackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Player {
	public MainPanel panel;
	public Rectangle playerRect;
	public int mX, mY, speed = 6, size = 80;

	public Player(MainPanel panel) {
		this.panel = panel;
		playerRect = new Rectangle();
		CreateAnPlayer();
	}

	public void CreateAnPlayer() {
		playerRect.setBounds(500, 300, size, size);
		mY = 0;
		mX = 0; 
	}

	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		panel.centerX = panel.playerImg.getWidth() / 2;
		panel.centerY = panel.playerImg.getHeight() / 2;
		AffineTransform oldAT = g2D.getTransform();
		g.translate(panel.centerX + playerRect.x, panel.centerY + playerRect.y);
		g2D.rotate(-panel.imageAngleRad);
		g.translate(-panel.centerX, -panel.centerY);
		g.drawImage(panel.playerImg, 0, 0, size, size, null);

		g2D.setTransform(oldAT);

	}

	public void playerUpdate() {
		if (mX == speed) {
			if (playerRect.x + playerRect.width <= panel.getWidth())
				playerRect.x += mX;
		} else if (mX == -speed) {
			if (playerRect.x >= 0)
				playerRect.x += mX;
		} else {
			playerRect.x += mX;
		}

		if (mY == speed) {
			if (playerRect.y + playerRect.width <= panel.getHeight())
				playerRect.y += mY;
		} else if (mY == -speed) {
			if (playerRect.y >= 0)
				playerRect.y += mY;
		} else {
			playerRect.y += mY;
		}
	}

	public void KeyPressed(int key) {
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			mY = -speed;
		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			mY = speed;
		} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			mX = -speed;
		} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			mX = speed;
		}
	}

	public void keyReleased(int key) {
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			mY = 0;

		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			mY = 0;
		} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {

			mX = 0;
		} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			mX = 0;

		}
	}

}
