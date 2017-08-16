package ShootForYourLifePackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy {
	public MainPanel panel;
	public Random random = new Random();
	public ArrayList<Rectangle> smallEnemyRect, bigEnemyRect;
	public int smallEnemysize = 50, bigEnemysize = 120, saveRandomNo, speed = 1, gameWidth = 1000, gameHeight = 700;
	public Player player;
	public Thread roatedThread;
	public BufferedImage newImage;
	public ArrayList<Integer> healthForSmall, healthForBigger;

	public Enemy(MainPanel panel, Player player) {
		this.panel = panel;
		this.player = player;
		smallEnemyRect = new ArrayList<>();
		bigEnemyRect = new ArrayList<>();
		healthForSmall = new ArrayList<>();
		healthForBigger = new ArrayList<>();
		CreatedAnEnemy(1);
		CreatedAnEnemy(2);
		Roatated();
	}

	public void CLoseAll() {
		healthForSmall.clear();
		healthForBigger.clear();
		smallEnemyRect.clear();
		bigEnemyRect.clear();
		CreatedAnEnemy(1);
		CreatedAnEnemy(2);
	}

	public void CreatedAnSmallEnemy(int x, int y) {
		int j = 6;
		for (int i = 6; i < 10; i += 2) {
			j -= 2;

			smallEnemyRect.add(new Rectangle(x + (i * 10), y + (j * 10), smallEnemysize, smallEnemysize));

			healthForSmall.add(1);
		}
	}

	public void CreatedAnEnemy(int whichOne) {
		saveRandomNo = random.nextInt(3);
		if (whichOne == 1) {

			if (saveRandomNo == 0) {
				smallEnemyRect.add(new Rectangle(+random.nextInt(gameWidth), -random.nextInt(gameHeight),
						smallEnemysize, smallEnemysize));
			} else if (saveRandomNo == 1) {
				smallEnemyRect.add(new Rectangle(-random.nextInt(gameWidth), +random.nextInt(gameHeight),
						smallEnemysize, smallEnemysize));
			} else if (saveRandomNo == 2) {

				smallEnemyRect.add(new Rectangle(random.nextInt(200) + gameWidth, random.nextInt(gameHeight),
						smallEnemysize, smallEnemysize));
			} else if (saveRandomNo == 3) {
				smallEnemyRect.add(new Rectangle(random.nextInt(gameWidth), random.nextInt(200) + gameHeight,
						smallEnemysize, smallEnemysize));
			}
			healthForSmall.add(1);
		} else {

			if (saveRandomNo == 0) {
				bigEnemyRect.add(new Rectangle(-random.nextInt(gameWidth), -random.nextInt(gameHeight), bigEnemysize,
						bigEnemysize));
			} else if (saveRandomNo == 1) {
				bigEnemyRect.add(new Rectangle(-random.nextInt(gameWidth), random.nextInt(gameHeight), bigEnemysize,
						bigEnemysize));
			} else if (saveRandomNo == 2) {
				bigEnemyRect.add(new Rectangle(random.nextInt(200) + gameWidth, random.nextInt(gameHeight),
						bigEnemysize, bigEnemysize));
			} else if (saveRandomNo == 3) {
				bigEnemyRect.add(new Rectangle(random.nextInt(gameWidth), random.nextInt(200) + gameHeight,
						bigEnemysize, bigEnemysize));
			}
			healthForBigger.add(3);
		}
	}

	public void paint(Graphics g) {

		for (int i = 0; i < smallEnemyRect.size(); i++) {
			g.drawImage(panel.smallEnemy, smallEnemyRect.get(i).x, smallEnemyRect.get(i).y, smallEnemyRect.get(i).width,
					smallEnemyRect.get(i).height, null);
		}

		for (int i = 0; i < bigEnemyRect.size(); i++) {
			g.drawImage(panel.bigEnemy, bigEnemyRect.get(i).x, bigEnemyRect.get(i).y, bigEnemyRect.get(i).width,
					bigEnemyRect.get(i).height, null);
		}
	}

	public BufferedImage rotate90ToRight(BufferedImage inputImage) {

		int width = inputImage.getWidth();
		int height = inputImage.getHeight();
		BufferedImage returnImage = new BufferedImage(height, width, inputImage.getType());

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				returnImage.setRGB(height - y - 1, x, inputImage.getRGB(x, y));

			}
		}
		return (panel.bigEnemy = returnImage);

	}

	public void enemyUpdate() {

		for (int i = 0; i < smallEnemyRect.size(); i++) {
			if (smallEnemyRect.get(i).x >= player.playerRect.x + 20)
				smallEnemyRect.get(i).x -= speed + 2;
			else {
				smallEnemyRect.get(i).x += speed + 2;
			}
			if (smallEnemyRect.get(i).y >= player.playerRect.y + 13)
				smallEnemyRect.get(i).y -= speed + 2;
			else {
				smallEnemyRect.get(i).y += speed + 2;
			}
			if (smallEnemyRect.get(i).intersects(new Rectangle(player.playerRect.x + 20, player.playerRect.y + 20,
					player.playerRect.width - 60, player.playerRect.height - 60))) {
				panel.GameOver();
			}
		}
		for (int i = 0; i < bigEnemyRect.size(); i++) {
			if (bigEnemyRect.get(i).x >= player.playerRect.x - 20)
				bigEnemyRect.get(i).x -= speed;
			else {
				bigEnemyRect.get(i).x += speed;
			}
			if (bigEnemyRect.get(i).y >= player.playerRect.y - 20)
				bigEnemyRect.get(i).y -= speed;
			else {
				bigEnemyRect.get(i).y += speed;
			}
			if (bigEnemyRect.get(i).intersects(new Rectangle(player.playerRect.x + 20, player.playerRect.y + 20,
					player.playerRect.width - 50, player.playerRect.height - 60))) {
				panel.GameOver();
			}
		}
	}

	public void Roatated() {

		roatedThread = new Thread(new Runnable() {
			int get = 0;

			@Override
			public synchronized void run() {
				while (true) {
					try {
						Thread.sleep(180);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					get++;
					if(panel.timer.isRunning()) {
					if (get == 50) {
						CreatedAnEnemy(1);
						CreatedAnEnemy(2);
						get = 0;
					}
					}else {
						get = 0;
					}

					rotate90ToRight(panel.bigEnemy);
					panel.repaint();
				}
			}
		});
	}

}
