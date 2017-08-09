
package JumpOverPaddlePackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Ball {
	public MainPanel panel;
	public Rectangle ballRect;
	public int moveX, posY, speed = 5, jumping = 150;

	public boolean jump = true;
	public Paddler paddler;

	public Ball(MainPanel panel, Paddler paddler) {
		this.panel = panel;
		this.paddler = paddler;
		ballRect = new Rectangle();
		CreatedAnBall();
		posY = ballRect.y - jumping;
	}

	public void CreatedAnBall() {
		ballRect.setBounds(200, 502, 50, 50);
	}

	public void paint(Graphics g) {
		g.drawImage(panel.ballImage, ballRect.x, ballRect.y, ballRect.width, ballRect.height, null);
	}

	public void BallUpdate() {
		panel.score = paddler.stage;
		if(ballRect.y<= -80) {
			panel.NextStage();
		}
		if (moveX == speed) {
			if (ballRect.x + ballRect.width + 5 <= panel.getWidth())
				ballRect.x += moveX;
		} else if (moveX == -speed) {
			if (ballRect.x >= 0 + 5)
				ballRect.x += moveX;
		} else {
			ballRect.x += moveX;
		}

		if (!jump) {
			ballRect.y += speed+1;
			if (ballRect.y >= 502) {
				jump = true;
				posY = ballRect.y - jumping;
			}

			for (int i = 0; i < paddler.paddlerRect.size(); i++) {
				if (ballRect.intersects(new Rectangle(paddler.paddlerRect.get(i).x, paddler.paddlerRect.get(i).y,
						paddler.paddlerRect.get(i).width, paddler.paddlerRect.get(i).height))) {
					jump = true;
					posY = ballRect.y - jumping;
					
				}
			}
		} else {
			if (posY <= ballRect.y) {
				ballRect.y -= speed+1;
				for (int i = 0; i < paddler.paddlerRect.size(); i++) {
					if (ballRect.intersects(new Rectangle(paddler.paddlerRect.get(i).x, paddler.paddlerRect.get(i).y+10,
							paddler.paddlerRect.get(i).width, paddler.paddlerRect.get(i).height))) {
						jump = false;
						
					}
				}
			} else {
				jump = false;
			}

		}
		panel.repaint();
	}

	public void KeyPressed(int key) {

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
			moveX = speed;
		else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
			moveX = -speed;

	}

	public void keyReleased(int key) {

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
			moveX = 0;
		else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
			moveX = 0;
	}

}
