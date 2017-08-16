package ShootForYourLifePackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Bullet {
	public MainPanel panel;
	public Enemy enemy;
	public int size = 25, speed = 5, save, saveIt;
	public ArrayList<Rectangle> bulletRect, explosion;
	public ArrayList<Integer> explosionTime;
	public BufferedImage explosionImage;

	public Bullet(MainPanel panel, Enemy enemy) {
		this.panel = panel;
		this.enemy = enemy;
		bulletRect = new ArrayList<>();
		explosion = new ArrayList<>();
		explosionTime = new ArrayList<>();

	}

	public void CLoseAll() {
		bulletRect.clear();
		explosion.clear();
		explosionTime.clear();
	}

	public void CreatedAnBullet(int x, int y) {

		bulletRect.add(new Rectangle(x + 25, y + 25, size, size));

	}

	public void paint(Graphics g) {
		for (int i = 0; i < bulletRect.size(); i++) {
			g.drawImage(panel.bulletImage, bulletRect.get(i).x, bulletRect.get(i).y, 30, 10, null);
		}
		for (int i = explosion.size() - 1; i >= 0; i--) {
			g.drawImage(ExplosionImages(i), explosion.get(i).x, explosion.get(i).y, explosion.get(i).width,
					explosion.get(i).height, null);
			if (saveIt >= 89) {
				explosion.remove(i);
				explosionTime.remove(i);

			}

		}
	}

	public void bulletUpdate() {

		for (int i = 0; i < bulletRect.size(); i++) {
			if (enemy.bigEnemyRect.get(0).x > 0 && enemy.bigEnemyRect.get(0).x < 1000 && enemy.bigEnemyRect.get(0).y > 0
					&& enemy.bigEnemyRect.get(0).y < 700) {
				if (enemy.bigEnemyRect.get(0).x + 50 <= bulletRect.get(i).x)
					bulletRect.get(i).x -= speed;
				else {
					bulletRect.get(i).x += speed;
				}
				if (enemy.bigEnemyRect.get(0).y + 60 <= bulletRect.get(i).y)
					bulletRect.get(i).y -= speed;
				else {
					bulletRect.get(i).y += speed;
				}
			} else if (enemy.smallEnemyRect.get(0).x > 0 && enemy.smallEnemyRect.get(0).x < 1000
					&& enemy.smallEnemyRect.get(0).y > 0 && enemy.smallEnemyRect.get(0).y < 700) {

				if (enemy.smallEnemyRect.get(0).x + 15 <= bulletRect.get(i).x)
					bulletRect.get(i).x -= speed;
				else {
					bulletRect.get(i).x += speed;
				}
				if (enemy.smallEnemyRect.get(0).y + 18 <= bulletRect.get(i).y)
					bulletRect.get(i).y -= speed;
				else {
					bulletRect.get(i).y += speed;
				}
			} else {
				bulletRect.get(i).x += speed;
				if (bulletRect.get(i).x >= 1000)
					bulletRect.remove(i);
			}

		}
		for (int j = enemy.bigEnemyRect.size() - 1; j >= 0; j--) {
			for (int i = bulletRect.size() - 1; i >= 0; i--) {

				if (bulletRect.get(i).intersects(enemy.bigEnemyRect.get(j))) {
					bulletRect.remove(i);
					save = enemy.healthForBigger.get(j);
					save--;
					enemy.healthForBigger.set(j, save);
					if (enemy.healthForBigger.get(j) <= 0) {
						if (j == enemy.smallEnemyRect.size() - 1)
							enemy.CreatedAnEnemy(2);
						explosion
								.add(new Rectangle(enemy.bigEnemyRect.get(j).x, enemy.bigEnemyRect.get(j).y, 100, 100));
						explosionTime.add(0);
						enemy.CreatedAnSmallEnemy(enemy.bigEnemyRect.get(j).x, enemy.bigEnemyRect.get(j).y);
						enemy.healthForBigger.remove(j);
						enemy.bigEnemyRect.remove(j);
						panel.score += 10;

					}

				}
			}
		}
		for (int j = enemy.smallEnemyRect.size() - 1; j >= 0; j--) {
			for (int i = bulletRect.size() - 1; i >= 0; i--) {

				if (bulletRect.get(i).intersects(enemy.smallEnemyRect.get(j))) {
					if (bulletRect.size() >= 0) {
						bulletRect.remove(i);
						save = enemy.healthForSmall.get(j);
						save--;
						enemy.healthForSmall.set(j, save);
						if (enemy.healthForSmall.get(j) <= 0) {
							if (j == enemy.smallEnemyRect.size() - 1)
								enemy.CreatedAnEnemy(1);
							explosion.add(
									new Rectangle(enemy.smallEnemyRect.get(j).x, enemy.smallEnemyRect.get(j).y, 50, 50));
							explosionTime.add(0);
							enemy.healthForSmall.remove(j);
							enemy.smallEnemyRect.remove(j);
							panel.score += 5;
						}

					}
				}
			}

		}

	}

	public BufferedImage ExplosionImages(int count) {

		saveIt = explosionTime.get(count);
		saveIt++;
		explosionTime.set(count, saveIt);
		try {
			explosionImage = ImageIO
					.read(new File("/home/sudoajay/Documents/workspace/Shoot  For Your Life/src/Images/expl/explosion_"
							+ saveIt + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return explosionImage;
	}

}
