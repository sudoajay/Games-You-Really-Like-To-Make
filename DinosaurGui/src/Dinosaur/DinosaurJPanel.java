package Dinosaur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class DinosaurJPanel extends JPanel implements Runnable {
	public JLabel snakeJlabel;
	public JLabel madeByJlabel;
	public Rectangle dino;
	public Rectangle cloud;
	public BufferedImage cloudIcon;
	public BufferedImage dinoIcon;
	public BufferedImage treeIcon;
	public int delay = 3;
	public Timer timer;
	public int ySpeed = 1;
	public boolean jump = false;
	public int score;
	public int highScore;
	public Thread threadScore;
	public Thread threadcloud;
	public Thread threadTree;
	public Thread threadSound;
	public boolean playerIsAlive;
	public boolean gameOn;
	public Random random;
	public ArrayList<Info> treeArray;
	public boolean pass;
	public boolean timerIsStop = false;
	public boolean jumping;
	public Clip clip;

	public DinosaurJPanel() {
		setPreferredSize(new Dimension(700, 400));
		Game();

		threadScore = new Thread(new Listener());
		threadTree = new Thread(this);

		treeArray = new ArrayList<>();
		addKeyListener(new Listener());
		setFocusable(true);
		timer = new Timer(delay, new Listener());
		dino = new Rectangle();
		cloud = new Rectangle();
		random = new Random();
		CloudLoop();
		image();

	}

	public void image() {
		try {
			cloudIcon = ImageIO.read(new File("/home/sudoajay/Documents/workspace/DinosaurGui/src/Images/Cloud.png"));
			dinoIcon = ImageIO.read(new File("/home/sudoajay/Documents/workspace/DinosaurGui/src/Images/Icon.jpg"));
			treeIcon = ImageIO.read(new File("/home/sudoajay/Documents/workspace/DinosaurGui/src/Images/Tree.png"));

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < treeArray.size(); i++) {
			g.drawImage(treeIcon, treeArray.get(i).x, treeArray.get(i).y, treeArray.get(i).width, treeArray.get(i).height, null, this);
		}
		g.drawImage(dinoIcon, dino.x, dino.y, dino.width, dino.height, null, this);

		g.drawImage(cloudIcon, cloud.x, cloud.y, cloud.width, cloud.height, null, this);

		g.drawLine(0, getHeight() - 22, getWidth(), getHeight() - 22);

		g.setColor(Color.GRAY);
		g.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		g.drawString("S - " + score, getWidth() - 60, 20);
		g.setColor(Color.GRAY);
		g.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		g.drawString("HS - " + highScore, getWidth() - 130, 20);

	}

	class Info {
		public int x;
		public int y;
		public int width;
		public int height;
		public int speed;

		public Info(int newX, int newY, int newWidth, int newHeight) {
			this.x = newX;
			this.y = newY;
			this.width = newWidth;
			this.height = newHeight;
			this.speed = 1;
		}

		public void update() {
			x -= speed;

			if (dino.intersects(new Rectangle(x + 8, y, width, height))) {
				if (highScore < score) {

					highScore = score;
				}
				score = 0;
				timer.stop();
				clip.stop();

				timerIsStop = true;

				GameRestart();
				treeArray.clear();
				treeArray.add(new Info(getWidth() - 220, getHeight() - 70, 50, 50));
				repaint();

			}
		}

	}

	class Listener implements KeyListener, ActionListener, Runnable {

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
			if (key == KeyEvent.VK_SPACE) {
				jumping = true;
				SoundEffect();

				if (!pass) {
					playerIsAlive = true;
					GameStart();
					threadScore.start();
					threadTree.start();
					threadcloud.start();
					threadSound.start();
				}

				pass = true;
				if (dino.y == getHeight() - 60) {
					jump = true;

				}
				if (!timer.isRunning())
					timerIsStop = false;
				playerIsAlive = true;
				GameStart();
				timer.start();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (jump) {
				if (dino.y > (getHeight() - 40) - 170) {
					dino.y -= ySpeed;
					repaint();

				} else {
					jump = false;

				}
			} else {

				if (dino.y < getHeight() - 60) {
					dino.y += ySpeed;
					repaint();

				}

			}
			cloud.x -= 1;
			repaint();
			for (int i = 0; i < treeArray.size(); i++) {
				treeArray.get(i).update();
			}
			repaint();
		}

		@Override
		public synchronized void run() {
			while (playerIsAlive) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!timerIsStop) {

					score += 1;
					repaint();

				} else {
					try {
						Thread.sleep(timer.getDelay());
					} catch (InterruptedException e) {
					}
				}

			}

		}

	}

	public void Game() {
		setLayout(null);
		snakeJlabel = new JLabel("Dinosaur");
		snakeJlabel.setForeground(Color.DARK_GRAY);
		snakeJlabel.setBounds(10, 109, 1000, 136);
		snakeJlabel.setFont(new Font("Dyuthi", Font.BOLD | Font.ITALIC, 120));
		add(snakeJlabel);

		madeByJlabel = new JLabel("Made By Sudo Ajay");
		madeByJlabel.setForeground(Color.DARK_GRAY);
		madeByJlabel.setBounds(473, 228, 196, 34);
		madeByJlabel.setFont(new Font("DejaVu Serif", Font.ITALIC, 20));
		add(madeByJlabel);
	}

	public void GameStart() {
		snakeJlabel.setText("");
		madeByJlabel.setText("");
		gameOn = true;
		dino.setBounds(20, getHeight() - 60, 40, 40);
		repaint();

	}

	public void GameRestart() {
		snakeJlabel.setText("Dinosaur");
		madeByJlabel.setText("Made By Sudo Ajay");
	}

	public void SoundEffect() {
		threadSound = new Thread(new Runnable() {

			@Override
			public synchronized void run() {
				while (playerIsAlive) {

					if (jumping) {
						try {
							File file = new File("/home/sudoajay/Documents/workspace/DinosaurGui/src/Sound/GameSound.wav");

							AudioInputStream ais = AudioSystem.getAudioInputStream(file);
							AudioFormat format = ais.getFormat();
							DataLine.Info info = new DataLine.Info(Clip.class, format);
							clip = (Clip) AudioSystem.getLine(info);
							clip.open(ais);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
							clip.start();

						
						jumping = false;
						
						Thread.sleep(50);
						clip.stop();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							Thread.sleep(timer.getDelay());
						} catch (InterruptedException e) {
						}
					}

				}
			}
		});
	}

	public void CloudLoop() {
		threadcloud = new Thread(new Runnable() {

			@Override
			public synchronized void run() {
				while (gameOn) {

					cloud.setBounds(730, random.nextInt(getHeight()) / 2, 100, 100);
					repaint();
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
	}

	@Override
	public synchronized void run() {
		int randomNo;
		while (playerIsAlive) {

			randomNo = random.nextInt(3) + 1;
			if (randomNo == 1) {
				// first case
				treeArray.add(new Info(getWidth() + 80, getHeight() - 70, 50, 50));
				repaint();
			}
			if (randomNo == 2) {
				// second case
				treeArray.add(new Info(getWidth() + 80, getHeight() - 70, 50, 50));
				treeArray.add(new Info(getWidth() + 100, getHeight() - 70, 50, 50));
				repaint();
			}
			if (randomNo == 3) {
				// third case
				treeArray.add(new Info(getWidth() + 80, getHeight() - 70, 50, 50));
				treeArray.add(new Info(getWidth() + 120, getHeight() - 70, 50, 50));
				repaint();
			}
			try {
				Thread.sleep(1800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
