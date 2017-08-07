package FlappyBird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyBirdJpanel extends JPanel {

	public Image flappyImage;
	public Rectangle cloud;
	public Rectangle flappy;
	public Thread cloudThread;
	public Thread pipeThread;
	public Thread scoreThread;
	public Thread threadSoundJump;
	public Thread threadSoundScore;
	public int delay = 5;
	public Timer timer;
	public BufferedImage cloudImage;
	public BufferedImage groundImage;
	public BufferedImage PipeImage;
	public BufferedImage Pipe1Image;
	public Random random;
	public ArrayList<PipeInfo> pipeArray;
	public ArrayList<PipeInfo> pipe1Array;
	public int score = 0;
	public int highScore = 0;
	public Clip clip;
	public boolean jumping;
	public boolean scoreCount;
	public boolean playerIsAlive = true;;

	public FlappyBirdJpanel() {
		setPreferredSize(new Dimension(800, 500));
		addKeyListener(new Lisener());
		setFocusable(true);
		addMouseListener(new Lisener());
		flappy = new Rectangle();
		timer = new Timer(delay, new Lisener());
		cloud = new Rectangle();
		pipeArray = new ArrayList<>();
		pipe1Array = new ArrayList<>();
		random = new Random();
		CloudLoop();
		PipeLoop();
		AllImages();
		Flappy();
		SoundEffectOnJumping();
		SoundEffectOnScore();

	}

	public void AllImages() {
		try {
			cloudImage = ImageIO
					.read(new File("/home/sudoajay/Documents/workspace/FlappyBirdGui/src/Images/cloud.png"));
			groundImage = ImageIO
					.read(new File("/home/sudoajay/Documents/workspace/FlappyBirdGui/src/Images/ground.png"));
			flappyImage = Toolkit.getDefaultToolkit()
					.createImage("/home/sudoajay/Documents/workspace/FlappyBirdGui/src/Images/tenor.gif");
			Pipe1Image = ImageIO
					.read(new File("/home/sudoajay/Documents/workspace/FlappyBirdGui/src/Images/Pipe1.png"));
			PipeImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/FlappyBirdGui/src/Images/Pipe.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < pipeArray.size(); i++) {
			g.drawImage(PipeImage, pipeArray.get(i).x, pipeArray.get(i).y, pipeArray.get(i).width,
					pipeArray.get(i).height, null, this);

		}
		for (int i = 0; i < pipe1Array.size(); i++) {
			g.drawImage(Pipe1Image, pipe1Array.get(i).x, pipe1Array.get(i).y, pipe1Array.get(i).width,
					pipe1Array.get(i).height, null, this);

		}
		g.drawImage(flappyImage, flappy.x, flappy.y, flappy.width, flappy.height, null, this);

		g.drawImage(cloudImage, cloud.x, cloud.y, cloud.width, cloud.height, null, this);
		g.drawImage(groundImage, 0, getHeight() - 80, getWidth() / 4, getHeight() / 4, null, this);
		g.drawImage(groundImage, 200, getHeight() - 80, getWidth() / 4, getHeight() / 4, null, this);
		g.drawImage(groundImage, 400, getHeight() - 80, getWidth() / 4, getHeight() / 4, null, this);
		g.drawImage(groundImage, 600, getHeight() - 80, getWidth() / 4, getHeight() / 4, null, this);
		g.setColor(Color.BLUE);
		g.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		g.drawString("S - " + score / 2, 10, 15);
		g.drawString("HS - " + highScore, 60, 15);
	}

	class PipeInfo {
		public int x;
		public int y;
		public int width;
		public int height;
		public int speed;
		public boolean got = false;
		public boolean pass = true;
		public int get;

		public PipeInfo(int newX, int newY, int newWidth, int newHeight) {
			this.x = newX;
			this.y = newY;
			this.width = newWidth;
			this.height = newHeight;
			speed = 5;
		}

		public void Update() {
			x -= 1;
			Icon restartIcon = new ImageIcon("/home/sudoajay/Documents/workspace/FlappyBirdGui/src/Images/Restart.png");
			if (flappy.intersects(new Rectangle(x, y, width, height)) || flappy.y == 385) {
				clip.stop();
				get = 385;
				while (pass) {
					if (get >= flappy.y) {
						flappy.y += 1;
						repaint();
					} else {
						pass = false;
					}
				}
				if (highScore < score) {
					highScore = score / 2;
				}

				timer.stop();
				int get = JOptionPane.showConfirmDialog(null, "Are You Sure To Play Again ? ", "Game Restart !",
						JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartIcon);
				if (get == 0) {
					score = 0;
					pipe1Array.clear();
					pipeArray.clear();
					pipe1Array.add(new PipeInfo(getWidth() - 250, 0, 100, 120));
					pipeArray.add(new PipeInfo(getWidth() - 250, getHeight() - 200, 100, 150));
					repaint();
					flappy.y = 150;

					timer.start();

				} else {
					System.exit(0);
				}

			}
			if (!got) {
				if (flappy.x > x) {
					score += 1;
					scoreCount = true;
					got = true;
				}

			}
		}

	}

	class Lisener implements KeyListener, ActionListener, MouseListener {
		boolean pass;

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ESCAPE) {
				System.exit(0);
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
			cloud.x -= 2;
			repaint();
			for (int i = 0; i < pipeArray.size(); i++) {
				pipeArray.get(i).Update();
				repaint();
			}

			for (int i = 0; i < pipe1Array.size(); i++) {
				pipe1Array.get(i).Update();
			}
			repaint();
			flappy.y += 1;
			repaint();

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!pass) {
				timer.start();
				cloudThread.start();
				pipeThread.start();
				threadSoundJump.start();
				threadSoundScore.start();
			}
			pass = true;
			jumping = true;
			if (timer.isRunning()) {
				boolean move = true;
				int getHeight = flappy.y - 90;
				while (move) {
					if (getHeight <= flappy.y) {
						flappy.y -= 1;
						repaint();
					} else {
						move = false;
					}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public void Flappy() {
		flappy.setBounds(20, 150, 40, 40);
		repaint();
	}

	public void PipeLoop() {
		boolean run = true;
		pipeThread = new Thread(new Runnable() {

			@Override
			public synchronized void run() {
				while (run) {
					int get = random.nextInt(5) + 1;
					if (get == 1) {
						pipe1Array.add(new PipeInfo(getWidth() + 100, 0, 100, 120));
						pipeArray.add(new PipeInfo(getWidth() + 100, getHeight() - 200, 100, 150));
						repaint();
					} else if (get == 2) {
						pipe1Array.add(new PipeInfo(getWidth() + 100, 0, 100, 180));
						pipeArray.add(new PipeInfo(getWidth() + 100, getHeight() - 130, 100, 150));
						repaint();
					} else if (get == 3) {
						pipe1Array.add(new PipeInfo(getWidth() + 100, 0, 100, 220));
						pipeArray.add(new PipeInfo(getWidth() + 100, getHeight() - 100, 100, 100));
						repaint();
					} else if (get == 4) {
						pipe1Array.add(new PipeInfo(getWidth() + 100, 0, 100, 80));
						pipeArray.add(new PipeInfo(getWidth() + 100, getHeight() - 250, 100, 250));
						repaint();
					} else if (get == 5) {
						pipe1Array.add(new PipeInfo(getWidth() + 100, 0, 100, 60));
						pipeArray.add(new PipeInfo(getWidth() + 100, getHeight() - 280, 100, 280));
						repaint();
					}
					try {
						Thread.sleep(2500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});
	}

	public void ScoreLoop() {
		boolean run = true;
		scoreThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (run) {
					try {
						Thread.sleep(1800);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
	}

	public void CloudLoop() {
		boolean run = true;
		cloudThread = new Thread(new Runnable() {

			@Override
			public synchronized void run() {
				while (run) {
					try {
						cloud.setBounds(750, 10, 200, 200);
						Thread.sleep(6000);

						repaint();
					} catch (Exception e) {

						e.printStackTrace();
					}
				}

			}
		});
	}

	public void SoundEffectOnJumping() {
		threadSoundJump = new Thread(new Runnable() {

			@Override
			public void run() {

				while (playerIsAlive) {

					if (jumping) {
						try {

							File file = new File(
									"/home/sudoajay/Documents/workspace/FlappyBirdGui/src/Sound/GameSound.wav");

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

	public void SoundEffectOnScore() {
		threadSoundScore = new Thread(new Runnable() {

			@Override
			public void run() {
				while (playerIsAlive) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (scoreCount) {
						try {
							File file = new File(
									"/home/sudoajay/Documents/workspace/FlappyBirdGui/src/Sound/ScoreCount.wav");
							AudioInputStream ais = AudioSystem.getAudioInputStream(file);
							AudioFormat format = ais.getFormat();
							DataLine.Info info = new DataLine.Info(Clip.class, format);
							clip = (Clip) AudioSystem.getLine(info);
							clip.open(ais);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
							clip.start();

						
						scoreCount = false;
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
}
