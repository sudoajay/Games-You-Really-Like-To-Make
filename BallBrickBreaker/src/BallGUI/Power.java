package BallGUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

public class Power {
	public Paddler paddler;
	public BallBreakerJPanel panel;
	public int x, y, width = 30, height = 30, ySpeed = 1, getRandomNo;
	public Rectangle power1Rect;
	public Rectangle power2Rect;
	public Image arrowLeft;
	public Image arrowRigth;
	public Image health;
	public Image Bomb;
	public Thread threadPowerTimer;
	public boolean power, powerOn, touchBomb, paddleTouch;
	public Random random;

	public Power(Paddler paddler, BallBreakerJPanel panel) {
		this.paddler = paddler;
		this.panel = panel;
		power1Rect = new Rectangle(-200, -200, width, height);
		power2Rect = new Rectangle(-200, -200, width, height);
		PowerTimer();
		threadPowerTimer.start();
		random = new Random();
		ImageLoad();
	}

	public void ImageLoad() {
		try {

			Bomb = Toolkit.getDefaultToolkit()
					.createImage("/home/sudoajay/Documents/workspace/BallBrickBreaker/src/Images/Bomb.gif");
			health = Toolkit.getDefaultToolkit()
					.createImage("/home/sudoajay/Documents/workspace/BallBrickBreaker/src/Images/Health.gif");
			arrowLeft = Toolkit.getDefaultToolkit()
					.createImage("/home/sudoajay/Documents/workspace/BallBrickBreaker/src/Images/Arrow-Left.gif");
			arrowRigth = Toolkit.getDefaultToolkit()
					.createImage("/home/sudoajay/Documents/workspace/BallBrickBreaker/src/Images/Arrow-Rigth.gif");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void GetLocation(int x, int y) {
		this.x = x;
		this.y = y;

		PowerRectLocation();
	}

	public void paint(Graphics g) {
		if (getRandomNo == 1) {
			g.drawImage(arrowLeft, power1Rect.x, power1Rect.y, power1Rect.width, power1Rect.height, null);
			g.drawImage(arrowRigth, power2Rect.x, power2Rect.y, power2Rect.width, power2Rect.height, null);
		} else if (getRandomNo == 2) {
			g.drawImage(arrowLeft, power1Rect.x, power1Rect.y, power1Rect.width, power1Rect.height, null);
			g.drawImage(arrowRigth, power2Rect.x, power2Rect.y, power2Rect.width, power2Rect.height, null);
		} else if (getRandomNo == 3) {
			g.drawImage(health, power1Rect.x, power1Rect.y, power1Rect.width, power1Rect.height, null);
		} else if (getRandomNo == 4) {
			g.drawImage(Bomb, power1Rect.x, power1Rect.y, power1Rect.width, power1Rect.height, null);
		}
	}

	public void Update() {
		power1Rect.y += ySpeed;
		power2Rect.y += ySpeed;

		if (paddler.paddleRect
				.intersects(new Rectangle(power1Rect.x, power1Rect.y, power1Rect.width, power1Rect.height))) {
			paddleTouch = true;
			if (getRandomNo == 1) {

				paddler.paddleRect.width = 60;
			} else if (getRandomNo == 2) {
				paddler.paddleRect.width = 150;

			} else if (getRandomNo == 3) {

				panel.life++;

			} else if (getRandomNo == 4) {

				touchBomb = true;

			}
			power = true;

			power1Rect.setBounds(-200, -200, width, height);
			power2Rect.setBounds(-200, -200, width, height);

		}

	}

	public void PowerTimer() {
		threadPowerTimer = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (power) {
						power = false;
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (!power) {
							paddler.paddleRect.width = 100;
						}
						touchBomb = false;

					}

					try {

						Thread.sleep(5000);
						powerOn = false;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		});
	}

	public void PowerRectLocation() {
		if (getRandomNo == 1) {
			power1Rect.setBounds(x + 15, y, width, height);
			power2Rect.setBounds(x - 15, y, width, height);
		} else if (getRandomNo == 2) {
			power2Rect.setBounds(x + 15, y, width, height);
			power1Rect.setBounds(x - 15, y, width, height);
		} else if (getRandomNo == 3) {
			if (random.nextInt(3) + 1 == 1)
				power1Rect.setBounds(x, y, width + 15, height + 15);
		} else if (getRandomNo == 4) {
			if (random.nextInt(2) + 1 == 1)
				power1Rect.setBounds(x, y, width + 10, height + 10);
		}

	}

	public int returnRandomNo() {
		getRandomNo = random.nextInt(4) + 1;

		return getRandomNo;
	}
}
