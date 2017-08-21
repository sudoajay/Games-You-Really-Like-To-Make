package Snake;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Snake {
	public int x, y, width = 25, height = 25, angle = 2, getX, getY, checkNo = 2, count;
	public Rectangle snakeHead, snakeFood, snakeBigFood;
	public BufferedImage rightMouth, leftMouth, upMouth, downMouth, snake, bigFood;
	public Thread runSnake, threadSound,threadBigSound;
	public Icon icon;
	public ArrayList<Snake> snakeArray;
	public boolean intersect, DRS = false, sound , bigSound ,checkForTouch ;
	public SnakeJPanel panel;
	public Random random;
	public Clip clip;

	public Snake(int x, int y, SnakeJPanel panel) {
		this.x = x;
		this.y = y;
		this.panel = panel;
		random = new Random();
		snakeArray = new ArrayList<>();
		snakeHead = new Rectangle(x, y, width, height);
		LoadImage();
		snakeBigFood = new Rectangle();
	}

	public void LoadImage() {
		try {
			rightMouth = ImageIO.read(new File("/home/sudoajay/Documents/workspace/SnakeGui/src/Images/rightmouth.png"));
			leftMouth = ImageIO.read(new File("/home/sudoajay/Documents/workspace/SnakeGui/src/Images/leftmouth.png"));
			upMouth = ImageIO.read(new File("/home/sudoajay/Documents/workspace/SnakeGui/src/Images/upmouth.png"));
			downMouth = ImageIO.read(new File("/home/sudoajay/Documents/workspace/SnakeGui/src/Images/downmouth.png"));
			snake = ImageIO.read(new File("/home/sudoajay/Documents/workspace/SnakeGui/src/Images/snake.png"));
			icon = new ImageIcon("/home/sudoajay/Documents/workspace/SnakeGui/src/Images/Restart.png");
			bigFood = ImageIO.read(new File("/home/sudoajay/Documents/workspace/SnakeGui/src/Images/BigFood.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		// angle in anticlock
		if (angle == 1)
			g.drawImage(upMouth, snakeHead.x, snakeHead.y, null);
		else if (angle == 2)
			g.drawImage(rightMouth, snakeHead.x, snakeHead.y, null);
		else if (angle == 3)
			g.drawImage(downMouth, snakeHead.x, snakeHead.y, null);
		else if (angle == 4)
			g.drawImage(leftMouth, snakeHead.x, snakeHead.y, null);
		for (int i = 0; i < snakeArray.size(); i++) {

			g.drawImage(snake, snakeArray.get(i).x, snakeArray.get(i).y, null);
		}
		if (count == 5) {
			g.drawImage(bigFood, snakeBigFood.x, snakeBigFood.y, snakeBigFood.width, snakeBigFood.height, null, null);

		} else {
			g.drawImage(null, -100, -100, 0, 0, null, null);
		}
	}

	public void KeyPressed(int key) {
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			if (angle != 3) {
				angle = 1;

			}
		} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			if (angle != 4) {
				angle = 2;
			}
		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			if (angle != 1) {
				angle = 3;
			}
		} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			if (angle != 2) {
				angle = 4;
			}
		}

	}

	public void Create() {
		for (int i = 0; i < 3; i++) {
			snakeArray.add(new Snake(-20, -20, null));

		}

	}

	public void SnakeMove() {
		for (int i = snakeArray.size() - 1; i >= 0; i--) {
			if (i == 0) {
				snakeArray.get(i).x = snakeHead.x;
				snakeArray.get(i).y = snakeHead.y;
			} else {
				snakeArray.get(i).x = snakeArray.get(i - 1).x;
				snakeArray.get(i).y = snakeArray.get(i - 1).y;
			}
		}
		if (angle == 1) {
			snakeHead.y -= 20;
		} else if (angle == 2) {
			snakeHead.x += 20;
		} else if (angle == 3) {
			snakeHead.y += 20;
		} else if (angle == 4) {
			snakeHead.x -= 20;
		}
		if (panel.noForMode == 0) {
			if (snakeHead.y <= 0) {
				snakeHead.y = 600;
			} else if (snakeHead.x <= 0) {
				snakeHead.x = 800;
			} else if (snakeHead.y >= 600) {
				snakeHead.y = 0;
			} else if (snakeHead.x >= 800) {
				snakeHead.x = 0;
			}
		} else {
			if ((snakeHead.y <= 0 || snakeHead.x <= 0) || (snakeHead.y >= 580 || snakeHead.x >= 780)) {
				GameEnd();

			}
		}
		CollisionGameEnd();

	}

	public void GetBoundFood(int x, int y, int width, int height) {
		snakeFood = new Rectangle(x, y, width, height);
	}

	public void CollisionGameEnd() {
		for (int i = 0; i < snakeArray.size(); i++) {
			if (snakeHead.x == snakeArray.get(i).x && snakeHead.y == snakeArray.get(i).y) {

				GameEnd();
			}
		}
	}

	public void GameEnd() {
		panel.timer.stop();
		int getInput2 = JOptionPane.showConfirmDialog(null, "Congrats Your Score is - " + panel.score,
				" Restart The Game ", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, icon);
		if (getInput2 == 0) {
			panel.Restart();
			count = 0;
		} else {
			System.exit(0);
		}
		snakeHead.x = 150;
		snakeHead.y = 150;
		angle = 2;
	}

	public void IntersectSnake() {
		runSnake = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (snakeBigFood.intersects(snakeHead) && !checkForTouch) {
						panel.touches = false;
						checkForTouch = true;
						panel.score += (100 - panel.progressBar.getValue()) ;
						panel.nowOn = false;
						count = 0;
						panel.progressBar.setVisible(false);
						panel.progressBar.setValue(0);
						panel.progressTime = 0;
						bigSound = true;
					}
					if (snakeFood.intersects(snakeHead)) {
						checkForTouch = false;
						intersect = true;
						sound = true;
						panel.score += 5;
						if (!panel.touches) {
							count++;
							DRS = true;
							snakeBigFood.setBounds(-200, -200, 100, 100);

						}
						if (count == 5 && DRS) {
							DRS = false;
							snakeBigFood.setBounds(random.nextInt(500) + 100, random.nextInt(400) + 100, 100, 100);
							panel.touches = true;
							panel.nowOn = true;

						}

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						}
						SnakeLonger();

					}

				}
			}
		});

	}

	public void SnakeLonger() {
		int get = snakeArray.size() - 1;
		for (int i = get; i < get + 3; i++) {
			snakeArray.add(new Snake(-20, -20, null));
		}

	}

	public void SoundEffect() {
		threadSound = new Thread(new Runnable() {

			@Override
			public synchronized void run() {
				while (true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (sound) {
						try {
							File file = new File("/home/sudoajay/Documents/workspace/SnakeGui/src/Images/Sound.wav");

							AudioInputStream ais = AudioSystem.getAudioInputStream(file);
							AudioFormat format = ais.getFormat();
							DataLine.Info info = new DataLine.Info(Clip.class, format);
							clip = (Clip) AudioSystem.getLine(info);
							clip.open(ais);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
							clip.start();
							Thread.sleep(200);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sound = false;
						clip.stop();
					}
				}
			}
		});
	}
	public void SoundEffectBigFood() {
		threadBigSound = new Thread(new Runnable() {

			@Override
			public synchronized void run() {
				while (true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (bigSound) {
						try {
							File file = new File("/home/sudoajay/Documents/workspace/SnakeGui/src/Images/Powerup.wav");

							AudioInputStream ais = AudioSystem.getAudioInputStream(file);
							AudioFormat format = ais.getFormat();
							DataLine.Info info = new DataLine.Info(Clip.class, format);
							clip = (Clip) AudioSystem.getLine(info);
							clip.open(ais);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
							clip.start();
							Thread.sleep(250);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						bigSound = false;
						clip.stop();
					}
				}
			}
		});
	}


}
