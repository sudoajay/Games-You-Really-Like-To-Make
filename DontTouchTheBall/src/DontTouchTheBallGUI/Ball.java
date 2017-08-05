package DontTouchTheBallGUI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.management.monitor.CounterMonitorMBean;
import javax.xml.xpath.XPath;

public class Ball {
	public ArrayList<Rectangle> rectBall;
	public Player player;
	public Bullet bullet;
	public MainJPanel panel;
	public int ySpeed = 2, xSpeed, countForAdd, count, minJump, maxJump, getSize;
	public Thread moveBallThread, ballCollisionWithBullet, ballCollisionWithPlayer;
	public boolean MoveUp;
	public ArrayList<Integer> saveSpeed;
	public ArrayList<Boolean> saveUpAndDown;

	public Ball(Bullet bullet, Player player, MainJPanel panel) {
		this.panel = panel;
		this.bullet = bullet;
		this.player = player;
		rectBall = new ArrayList<>();
		saveSpeed = new ArrayList<>();
		saveUpAndDown = new ArrayList<>();
		BallMoving();
		BallCollisionWithBullet();
		BallCollisionWithPlayer();
	}

	public void CreateBalls(int x, int y, int width, int height) {

		rectBall.add(new Rectangle(x, y, width, height));
		saveUpAndDown.add(false);
	}

	public void paint(Graphics g) {
		for (int i = 0; i < rectBall.size(); i++) {

			g.drawImage(panel.ballImage, rectBall.get(i).x, rectBall.get(i).y, rectBall.get(i).width,
					rectBall.get(i).height, null);
		}
	}

	public void BallUpdate() {
		count = 0;
		getSize = rectBall.size();
		for (int i = 0; i < getSize; i++) {
			checkForJumping(i);
			MoveUp = saveUpAndDown.get(count);
			if (!MoveUp) {
				rectBall.get(i).y += ySpeed;
				if (rectBall.get(i).y >= maxJump)

					saveUpAndDown.set(count, true);

			} else {
				rectBall.get(i).y -= ySpeed;
				if (rectBall.get(i).y <= minJump)
					saveUpAndDown.set(count, false);
			}
			if (rectBall.get(i).x >= 1300 - rectBall.get(i).width || rectBall.get(i).x <= 0) {
				saveSpeed.set(count, -saveSpeed.get(count));

			}
			xSpeed = saveSpeed.get(count);
			rectBall.get(i).x += xSpeed;
			count++;
			
			
		}
	}

	public void checkForJumping(int no) {
		if (rectBall.get(no).width == 150) {

			maxJump = 500;
			minJump = 0;
		} else if (rectBall.get(no).width == 120) {
			maxJump = 500 + 23;
			minJump = 80;
		} else if (rectBall.get(no).width == 90) {
			maxJump = 500 + 40;
			minJump = 150;
		} else if (rectBall.get(no).width == 60) {
			maxJump = 500 + 70;
			minJump = 220;
		}
	}

	public void BallMoving() {
		moveBallThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (panel.timer.isRunning()) {
						if(panel.mX  != 0) {
							if(panel.mX  == -1) {
							if(player.rectPlayer.x >=0)
							player.rectPlayer.x +=panel.mX ;
							}else {
								if( player.rectPlayer.x +player.rectPlayer.width <= 1300) {
									player.rectPlayer.x +=panel.mX ;
								}
							}
						}else {
							player.rectPlayer.x +=panel.mX ;
						}
					}
				}
			}
		});

	}

	public void BallCollisionWithBullet() {
		ballCollisionWithBullet = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					for (int i = 0; i < rectBall.size(); i++) {

						if (rectBall.get(i).contains(bullet.rectBullet)) {
							bullet.rectBullet.setBounds(0, 0, 0, 0);

							if (rectBall.get(i).width - 30 >= 60) {

								for (int j = 0; j < 2; j++) {
									if (countForAdd % 2 == 0) {
										CreateBalls(rectBall.get(i).x, rectBall.get(i).y, rectBall.get(i).width - 30,
												rectBall.get(i).height - 30);
									} else {
										CreateBalls(rectBall.get(i).x, rectBall.get(i).y, rectBall.get(i).width - 30,
												rectBall.get(i).height - 30);
									}
									countForAdd++;
								}

							}

							saveSpeed.remove(i);
							saveSpeed.add(1);
							saveSpeed.add(-1);
							rectBall.remove(i);
							saveUpAndDown.remove(i);

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;

						}
					}
				}

			}
		});

	}

	public void BallCollisionWithPlayer() {
		ballCollisionWithPlayer = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (int i = 0; i < rectBall.size(); i++) {
						if (player.rectPlayer.intersects(new Rectangle(rectBall.get(i).x + 40, rectBall.get(i).y,
								rectBall.get(i).width / 2, rectBall.get(i).height / 2))) {
							panel.life--;
							if (panel.life == 0)
								panel.GameOver(1);
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}

			}
		});
	}
}
