package MoveForwardPackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Ball {
	public MainPanel panel;
	public Rectangle ballRect;
	public Paddler paddler;
	public Thread threadBallToJump;
	public boolean jump;
	public int savePosit, mX;

	public Ball(MainPanel panel, Paddler paddler) {
		this.panel = panel;
		this.paddler = paddler;
		ballRect = new Rectangle();
		CreateAnBall();

	}

	public void CreateAnBall() {
		ballRect.setBounds(100, 250, 30, 30);
	}

	public void paint(Graphics g) {
		g.drawImage(panel.ballImage, ballRect.x, ballRect.y, ballRect.width, ballRect.height, null);
	}

	public void ballUpdate() {

		if (!jump) {
			ballRect.y += 5;
			for (int i = 0; i < paddler.arrayForPaddler.size(); i++) {
				if (ballRect.intersects(paddler.arrayForPaddler.get(i))) {
					jump = true;
					savePosit = ballRect.y - 200;

				}
			}
		} else {
			if (savePosit <= ballRect.y && ballRect.y >= 0)
				ballRect.y -= 5;

			else {
				jump = false;
			}

		}
		if (mX == -3) {
			if (ballRect.x >= 0+5)
				ballRect.x += mX;
		} else if (mX == 3) {
			if (ballRect.x + ballRect.width + 5 <= 1300)
			ballRect.x += mX;
		} else {
			
			ballRect.x += mX;
		}
		panel.repaint();

	}

	public void keyPressed(int key) {

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {

			mX = -3;

		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {

			mX = +3;

		}

	}

	public void KeyReleased(int key) {
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {

			mX = 0;
		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			mX = 0;
		}
	}

}