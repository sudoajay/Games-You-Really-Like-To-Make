package NinjaBallPackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.plaf.basic.BasicLabelUI;

public class Ball {
	public MainPanel panel;
	public Rectangle rectBall;
	public int mX, speed = 6, mY = 434, jumpingPower = 250, count, no;
	public boolean jump, move, nowJump;
	public ArrayList<Integer> saveI;
	public Layout layout;
	public Thread fallThread;
	public Random random = new Random();

	public Ball(MainPanel panel, Layout layout) {
		this.panel = panel;
		this.layout = layout;
		saveI = new ArrayList<>();
		rectBall = new Rectangle();
		Run();
		CreatedAnBall();
	}

	public void CreatedAnBall() {
		rectBall.setBounds(150, 435, 60, 60);
	}

	public void paint(Graphics g) {
		g.drawImage(panel.ballImage, rectBall.x, rectBall.y, rectBall.width, rectBall.height, null);
	}

	public void BallUpdate() {

		for (int i = 0; i < layout.movingDownBricks.size(); i++) {
			no++;

			if (no < 5)
				layout.movingDownBricks.get(i).y--;
			else if (no < 9) {

				layout.movingDownBricks.get(i).y++;
				if (no == 8)
					no = 0;

			}

			if (layout.movingDownBricks.get(i).y == -50) {
				layout.movingDownBricks.get(i).y = 800;
			} else if (layout.movingDownBricks.get(i).y == 800) {
				layout.movingDownBricks.get(i).y = -50;
			}

		}
		if (move) {
			if (mX == speed) {
				if (layout.rectBricks.get(layout.rectBricks.size() - 1).x >= panel.getWidth() && rectBall.x >= 150) {
					for (int i = 0; i < layout.movingDownBricks.size(); i++) {
						layout.movingDownBricks.get(i).x -= speed;
					}
					for (int i = 0; i < layout.fallDownBricks.size(); i++) {
						layout.fallDownBricks.get(i).x -= speed;
					}
					for (int i = 0; i < layout.extraBricks.size(); i++) {
						layout.extraBricks.get(i).x -= speed;
					}
					for (int i = 0; i < layout.standBricks.size(); i++) {
						layout.standBricks.get(i).x -= speed;
					}
					for (int i = 0; i < layout.rectBricks.size(); i++) {
						layout.rectBricks.get(i).x -= speed;
					}
				} else {
					if (layout.rectBricks.get(layout.rectBricks.size() - 1).x <= panel.getWidth()) {
						if (rectBall.x + rectBall.width + 5 <= panel.getWidth()) {
							rectBall.x += speed;
						}else {
							panel.GameOver(2);
						}

					}
					if (rectBall.x <= 150)
						rectBall.x += speed;
				}
			} else {

				if (layout.rectBricks.get(0).x <= -5 && rectBall.x <= 150) {
					for (int i = 0; i < layout.movingDownBricks.size(); i++) {
						layout.movingDownBricks.get(i).x += speed;
					}
					for (int i = 0; i < layout.fallDownBricks.size(); i++) {
						layout.fallDownBricks.get(i).x += speed;
					}
					for (int i = 0; i < layout.extraBricks.size(); i++) {
						layout.extraBricks.get(i).x += speed;
					}
					for (int i = 0; i < layout.standBricks.size(); i++) {
						layout.standBricks.get(i).x += speed;
					}
					for (int i = 0; i < layout.rectBricks.size(); i++) {
						layout.rectBricks.get(i).x += speed;
					}
				} else {
					if (layout.rectBricks.get(layout.rectBricks.size() - 1).x <= panel.getWidth()) {
						if (rectBall.x >= 150)
							;
						rectBall.x -= speed;
					}
					if (rectBall.x >= 0 + 5)
						rectBall.x -= speed;

				}
			}
			jump = true;
		}
		if (jump) {
			if (mY <= rectBall.y) {
				rectBall.y -= speed;

				for (int i = 0; i < layout.standBricks.size(); i++) {

					if (rectBall.intersects(layout.standBricks.get(i))) {
						mY += jumpingPower;
					}
				}
				for (int i = 0; i < layout.movingDownBricks.size(); i++) {

					if (rectBall.intersects(layout.movingDownBricks.get(i))) {
						mY += jumpingPower;
					}
				}
				for (int i = 0; i < layout.fallDownBricks.size(); i++) {

					if (rectBall.intersects(layout.fallDownBricks.get(i))) {
						mY += jumpingPower;
					}
				}
				if (mY >= rectBall.y)
					mY += jumpingPower;
			} else if (mY >= rectBall.y) {
				if (rectBall.y < 435) {
					rectBall.y += speed;
				} else {
					for (int i = 0; i < layout.rectBricks.size(); i++) {
						if (rectBall.intersects(
								new Rectangle(layout.rectBricks.get(i).x + 10, layout.rectBricks.get(i).y - 5,
										layout.rectBricks.get(i).width / 2 - 20, layout.rectBricks.get(i).height))) {

							jump = false;
							break;
						}

					}

					if (jump) {
						rectBall.y += speed;
					}

				}

				for (int i = 0; i < layout.fallDownBricks.size(); i++) {

					if (rectBall.intersects(new Rectangle(layout.fallDownBricks.get(i).x + 20,
							layout.fallDownBricks.get(i).y - 5, layout.fallDownBricks.get(i).width / 2 - 22,
							layout.fallDownBricks.get(i).height - 40))) {
						rectBall.y = layout.fallDownBricks.get(i).y - 60;
						nowJump = true;
						saveI.add(i);

					}
					mY += jumpingPower;

					if (mY <= rectBall.y)
						jump = false;

				}
				for (int i = 0; i < layout.standBricks.size(); i++) {

					if (rectBall
							.intersects(new Rectangle(layout.standBricks.get(i).x + 20, layout.standBricks.get(i).y - 5,
									layout.standBricks.get(i).width / 2 - 22, layout.standBricks.get(i).height - 40))) {
						rectBall.y = layout.standBricks.get(i).y - 60;
						jump = false;
					}
					mY += jumpingPower;

					if (mY <= rectBall.y)
						jump = false;
				}
				for (int i = 0; i < layout.movingDownBricks.size(); i++) {

					if (rectBall.intersects(new Rectangle(layout.movingDownBricks.get(i).x + 20,
							layout.movingDownBricks.get(i).y - 5, layout.movingDownBricks.get(i).width / 2 - 22,
							layout.movingDownBricks.get(i).height - 40))) {
						rectBall.y = layout.movingDownBricks.get(i).y - 60;
						nowJump = true;
					}
					mY += jumpingPower;

					if (mY <= rectBall.y)
						jump = false;
				}
				for (int i = 0; i < layout.extraBricks.size(); i++) {
					if (rectBall.intersects(new Rectangle(layout.extraBricks.get(i).x + 50, layout.extraBricks.get(i).y,
							layout.extraBricks.get(i).width / 2 - 22, layout.extraBricks.get(i).height))) {

						rectBall.x = layout.extraBricks.get(i).x + 50;
						rectBall.y += 1;
					} else if (rectBall
							.intersects(new Rectangle(layout.extraBricks.get(i).x, layout.extraBricks.get(i).y,
									layout.extraBricks.get(i).width / 2 - 22, layout.extraBricks.get(i).height))) {

						rectBall.x = layout.extraBricks.get(i).x - 60;
						rectBall.y += 1;
					}

				}
			}

		}

	}

	public void KeyPressed(int key) {
		if (rectBall.y <= 650 && panel.timer.isRunning()) {
			if (key == KeyEvent.VK_UP) {
				if (nowJump) {
					jump = true;

					mY = rectBall.y - jumpingPower;
					nowJump = false;
				}
				if (!jump) {
					jump = true;
					mY = rectBall.y - jumpingPower;

				}

			}
			if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
				mX = speed;
				move = true;

			} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
				mX = -speed;
				move = true;
			}
		}
	}

	public void keyReleased(int key) {

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			move = false;

		} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			move = false;

		}
	}

	public void Run() {
		fallThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (!saveI.isEmpty()) {
						for (int i = 0; i < saveI.size(); i++) {
							if (count >= 100 * (1 + i)) {
								layout.fallDownBricks.get(saveI.get(i)).y += 1;
							}
							if (layout.fallDownBricks.get(layout.fallDownBricks.size() - 1).y == 800) {
								layout.fallDownBricks.clear();
								saveI.clear();

							}
							count++;
						}
					}
				}

			}
		});
	}
}
