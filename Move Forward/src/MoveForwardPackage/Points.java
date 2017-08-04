package MoveForwardPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Points {
	public MainPanel panel;
	public Ball ball;
	public int save;
	public Random random = new Random();
	public ArrayList<Rectangle> enemyBall, gamePointBall;

	public Points(MainPanel panel, Ball ball) {
		this.panel = panel;
		this.ball= ball;
		enemyBall = new ArrayList<>();
		gamePointBall = new ArrayList<>();
		GameStarting();
		
	}
public void GameStarting() {
	save = random.nextInt(5) + 1;
	for (int i = 0; i < save; i++) {
		CreateAnPoint(1);
	}
	save = random.nextInt(5) + 1;
	for (int i = 0; i < save; i++) {
		CreateAnPoint(2);
	}
}
	
	public void CreateAnPoint(int whichOne) {
		if (whichOne == 1) {
			enemyBall.add(new Rectangle(random.nextInt(800) + 1400, random.nextInt(200) + 200, 20, 20));
		} else {
			gamePointBall.add(new Rectangle(random.nextInt(800) + 1400, random.nextInt(100) + 250, 20, 20));
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		for (int i = 0; i < enemyBall.size(); i++) {
			g.fillOval(enemyBall.get(i).x, enemyBall.get(i).y, enemyBall.get(i).width, enemyBall.get(i).height);
		}
		g.setColor(Color.GREEN);
		for (int i = 0; i < gamePointBall.size(); i++) {
			g.fillOval(gamePointBall.get(i).x, gamePointBall.get(i).y, gamePointBall.get(i).width,
					gamePointBall.get(i).height);
		}
	}

	public void PointUpdate() {
		for (int i = 0; i < enemyBall.size(); i++) {
			enemyBall.get(i).x -= 2;
			if(ball.ballRect.intersects(enemyBall.get(i))) {
				panel.gameRestart();
			}
		}
		for (int i = 0; i < gamePointBall.size(); i++) {
			gamePointBall.get(i).x -= 2;
			if(ball.ballRect.intersects(gamePointBall.get(i))) {
				gamePointBall.remove(i);
				panel.score++;
				save = random.nextInt(3) + 1;
				for (int j = 0; j < save; j++) {
					CreateAnPoint(2);
				}
			}
		}
		if (enemyBall.get(enemyBall.size() - 1).x <= -10) {
			enemyBall.remove(enemyBall.size() - 1);
			save = random.nextInt(5) + 1;
			for (int i = 0; i < save; i++) {
				CreateAnPoint(1);
			}
		}
		if (gamePointBall.get(gamePointBall.size() - 1).x <= -10) {
			gamePointBall.remove(gamePointBall.size() - 1);
			save = random.nextInt(5) + 1;
			for (int i = 0; i < save; i++) {
				CreateAnPoint(2);
			}
		}
		
		
	}

}
