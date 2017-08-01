package BallGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	public BallBreakerJPanel panel;
	public int x, y, width, height, xSpeed, ySpeed;
	public Rectangle ballRect;
	public Paddler paddler;
	public Boolean nextLevel;
	public Power power;
	public Random random = new Random();

	public Ball(BallBreakerJPanel panel, Paddler paddler, Power power) {
		this.panel = panel;
		this.paddler = paddler;
		this.power = power;
		x = 200;
		y = 500;
		width = 20;
		height = 20;
		xSpeed = 2;
		ySpeed = 2;
		ballRect = new Rectangle(x, y, width, height);
		Update();
		nextLevel = true;

	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(ballRect.x, ballRect.y, ballRect.width, ballRect.height);
	}

	public void Update() {

		ballRect.y -= ySpeed;
		ballRect.x += xSpeed;
		if (ballRect.y <=65) {

			RandomYNo();
			ySpeed = -ySpeed;
		} else if (ballRect.x <= 35 || ballRect.x + ballRect.width + 35 >= panel.getWidth()) {
			RandomXNo();
			xSpeed = -xSpeed;
		} else if (ballRect.intersects(new Rectangle(paddler.paddleRect.x, paddler.paddleRect.y-10,
				paddler.paddleRect.width - 75, paddler.paddleRect.height))) {

			if (xSpeed < 0) {
				
				ySpeed = -ySpeed;

			} else {
				
				ySpeed = -ySpeed;
				xSpeed = -xSpeed;
			}

		} else if (ballRect.intersects(new Rectangle(paddler.paddleRect.x, paddler.paddleRect.y-10,
				paddler.paddleRect.width - (paddler.paddleRect.width / 4), paddler.paddleRect.height))) {
		
			ySpeed = -ySpeed;

		} else if (ballRect.intersects(new Rectangle(paddler.paddleRect.x, paddler.paddleRect.y-10,
				paddler.paddleRect.width , paddler.paddleRect.height))) {

			if (xSpeed < 0) {
				
				ySpeed = -ySpeed;
				xSpeed = -xSpeed;
			} else {
				
				ySpeed = -ySpeed;

			}

		} else if (ballRect.y + ballRect.width - 300 >= panel.getHeight()) {
			panel.life--;
			if (panel.life != 0) {
				ballRect.x = 200;
				ballRect.y = 500;
				RandomYNo();
				RandomXNo();
				ySpeed = -ySpeed;
				xSpeed = -xSpeed;
			} else {

				panel.timer.stop();
				panel.GameRestart();
			}
		}

		panel.repaint();
	}

	public void RandomYNo() {

		if (ySpeed > 0) {
			ySpeed = random.nextInt(2) + 3;

		} else if (ySpeed < 0) {
			ySpeed = random.nextInt(2) - 4;
		}
	}

	public void RandomXNo() {

		if (xSpeed > 0) {
			xSpeed = random.nextInt(2) + 3;

		} else if (xSpeed < 0) {
			xSpeed = random.nextInt(2 ) -4;
		}
	}

}
