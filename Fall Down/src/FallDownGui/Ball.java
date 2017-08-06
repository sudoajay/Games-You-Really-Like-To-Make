package FallDownGui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Ball {
	public MainPanel panel;
	public Rectangle ballRect;
	public FallDownRectangle fall;
	public Random random = new Random();
	public int mX;

	public Ball(MainPanel panel, FallDownRectangle fall) {
		this.panel = panel;
		this.fall = fall;
		ballRect = new Rectangle();
		CreatedAnBall();
	}

	public void CreatedAnBall() {
		ballRect.setBounds(300, 300, 30, 30);

	}

	public void paint(Graphics g) {
		g.drawImage(panel.ballImage, ballRect.x, ballRect.y, ballRect.width, ballRect.height, null);
	}

	public void BallUpdates() {
		if (ballRect.y + ballRect.height <= 530)
			ballRect.y++;

		if (ballRect.y == 0)
			panel.gameRestart();
		for (int i = 0; i < fall.fallItRect.size(); i++) {
			if (ballRect.intersects(new Rectangle(fall.fallItRect.get(i).x, fall.fallItRect.get(i).y,
					fall.fallItRect.get(i).width, fall.fallItRect.get(i).height))) {
				ballRect.y = fall.fallItRect.get(i).y - 30;

			}
		}
		if (mX == -3) {
			if (ballRect.x >= 0 + 5) {
				ballRect.x += mX;
			}
		} else if (mX == +3) {
			if (ballRect.x + ballRect.width + 5 <= 700)
				ballRect.x += mX;
		} else {
			ballRect.x += mX;
		}
	}

	public void KeyPressed(int key) {
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_D) {

			mX = -3;
		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_A) {
			mX = +3;
		}
		panel.repaint();
	}

	public void keyReleased(int key) {
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_D) {

			mX = 0;
		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_A) {

			mX = 0;
		}
		panel.repaint();
	}
}
