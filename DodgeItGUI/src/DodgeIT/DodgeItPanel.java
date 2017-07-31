package DodgeIT;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
public class DodgeItPanel extends JPanel implements Runnable {

	public ArrayList<Info> enemy;
	public ArrayList<Info> star;
	public Random random = new Random();
	public Timer timer;
	public int delay = 30;
	public int health = 100;
	public int score = 0;
	public int highScore = 0;
	public boolean gameOn = true;
	public boolean FirstTime = true;;
	public JLabel DodgerLabel;
	public JLabel enterLabel;
	public JLabel spaceLabel;
	public Rectangle box;
	public Thread thread;
	public Thread threadTimer;
	public Thread threadSound;
	public BufferedImage starImage;
	public BufferedImage heroImage;
	public BufferedImage enemyImage;
	public Clip clip;

	public DodgeItPanel() {

		setBackground(Color.BLACK);
		Menu();

		enemy = new ArrayList<>();
		star = new ArrayList<>();
		timer = new Timer(delay, new Listener());
		thread = new Thread(this);
		threadTimer = new Thread(new Listener());
		threadSound = new Thread(new Info());
		box = new Rectangle();
		
		addKeyListener(new Listener());
		setFocusable(true);
		addMouseListener(new Listener());
		addMouseMotionListener(new Listener());
		ImagePanel();
		setLayout(null);

	}

	public void ImagePanel() {
		try {
			heroImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/DodgeItGUI/src/Images/Hero.jpg"));
			starImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/DodgeItGUI/src/Images/Stars.png"));
			enemyImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/DodgeItGUI/src/Images/Enemy.jpg"));
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		for (int i = 0; i < 30; i++) {
			star.add(new Info(random.nextInt(700) + 10, random.nextInt(600) + 10, 0, 0));
		}
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Info enemyInfo : enemy) {
			g.setColor(Color.GREEN);
			g.drawImage(enemyImage, enemyInfo.x, enemyInfo.y, enemyInfo.width, enemyInfo.height, null, this);
		}
		g.drawImage(heroImage, box.x, box.y, box.width, box.height, null, this);
		g.setColor(Color.RED);
		g.setFont(new Font("DejaVu Serif", Font.ITALIC, 15));
		g.drawString(health + " % ", (int) box.getX() + 2, (int) box.getY() - 5);

		for (Info info : star) {
			g.drawImage(starImage, info.x, info.y, null, this);
		}

		g.setColor(Color.blue);
		g.setFont(new Font("DejaVu Serif", Font.ITALIC, 15));
		g.drawString("S - " + score, 5, 15);
		if (!FirstTime) {
			g.setColor(Color.blue);
			g.setFont(new Font("DejaVu Serif", Font.ITALIC, 15));
			g.drawString("Hs - " + highScore, 60, 15);
		}
	}

	class Info  implements Runnable{
		public int x;
		public int y;
		public int width;
		public int height;
		public int ySpeed;

		public Info(){
			
		}
		public Info(int newX, int newY, int newWidth, int newHeight) {
			this.x = newX;
			this.y = newY;
			this.width = newWidth;
			this.height = newHeight;
			ySpeed = random.nextInt(5) + 5;
		}

		public void Update() {
			y += ySpeed;

			if (box.x < 0) {
				box.x = 2;
			} else if (box.x > getWidth() - box.getWidth()) {
				box.x = (int) (getWidth() - box.getWidth()) - 2;
			}
			if (box.y < 20) {
				box.y = 20;
			} else if (box.y > getHeight() - box.getHeight()) {
				box.y = (int) (getHeight() - box.getHeight()) - 2;
			}
			if (health <= 0) {
				health = 0;
				clip.stop();
				timer.stop();
				MenuRestart();
				FirstTime = false;

				if (highScore < score) {
					highScore = score;
				}

			} else {
				if (box.intersects(new Rectangle(x, y, width, height))) {
					health -= 5;

				}
			}

		}

		@Override
		public synchronized void run() {
			try {
				File file = new File("/home/sudoajay/Documents/workspace/GrabberGui/src/Images/Sound.wav");
				AudioInputStream ais = AudioSystem.getAudioInputStream(file);
				AudioFormat format = ais.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				clip = (Clip) AudioSystem.getLine(info);
				clip.open(ais);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				clip.start();
		        

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	class Listener implements KeyListener, MouseListener, ActionListener, MouseMotionListener, Runnable {

		boolean check = true;

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
			if (key == KeyEvent.VK_SPACE) {

				if (check) {
					gameStart();

				}
				check = false;
				if (health > 0) {
					if (!timer.isRunning()) {
						MenuStop();
						timer.start();
					}

				} else {
					gameRestart();
					MenuStop();
					timer.start();

				}
			}
			if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
				box.y = -10 + box.y;
			}
			if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
				box.y = +10 + box.y;
			}
			if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
				box.x = +10 + box.x;
			}
			if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
				box.x = -10 + box.x;
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			for (Info enemyInfo : enemy) {
				enemyInfo.Update();
			}

			repaint();

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (health > 0) {
				box.setBounds(e.getX() - box.width / 2, e.getY() - box.height / 2, 50, 40);
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (health > 0) {

				box.setBounds(e.getX() - box.width / 2, e.getY() - box.height / 2, 50, 40);
				repaint();
			}
		}

		@Override
		public synchronized void run() {
			while (gameOn) {
				if (health > 0) {
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					score += 1;

				} else {
					try {
						Thread.sleep(timer.getDelay());
					} catch (InterruptedException e) {
					}
				}
			}

		}
	}

	public void Menu() {
		DodgerLabel = new JLabel("DODGER");
		DodgerLabel.setForeground(Color.CYAN);
		DodgerLabel.setBounds(206, 68, 325, 150);
		DodgerLabel.setFont(new Font("Dyuthi", Font.BOLD | Font.ITALIC, 60));
		add(DodgerLabel);

		enterLabel = new JLabel("Press Space To Start The Game");
		enterLabel.setBounds(71, 306, 624, 100);
		enterLabel.setFont(new Font("DejaVu Serif", Font.ITALIC, 35));
		enterLabel.setForeground(Color.RED);
		add(enterLabel);

		spaceLabel = new JLabel("Game Made By Sudo Ajay");
		spaceLabel.setBounds(132, 194, 500, 100);
		spaceLabel.setFont(new Font("DejaVu Serif", Font.ITALIC, 34));
		spaceLabel.setForeground(Color.YELLOW);
		add(spaceLabel);
	}

	public void MenuRestart() {
		DodgerLabel.setText("DODGER");
		enterLabel.setText("Press Space To Restart The Game");
		spaceLabel.setText("Game Made By Sudo Ajay");

	}

	public void MenuStop() {
		DodgerLabel.setText("");
		enterLabel.setText("");
		spaceLabel.setText("");
	}

	public void gameStart() {

		
		thread.start();
		threadSound.start();
		threadTimer.start();
		gameOn = true;
	
	}

	public void gameRestart() {
		health = 100;
		score = 0;
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
	}

	

	@Override
	public synchronized void run() {
		
		while (gameOn) {
			if (health > 0) {
				try {
					Thread.sleep(300);

					enemy.add(new Info(random.nextInt(700), random.nextInt(100) - 400, 50, 40));

					repaint();
				} catch (Exception e) {
					System.out.println(e.toString());
					e.printStackTrace();
				}

			} else {

				try {
					Thread.sleep(timer.getDelay());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				;

			}
		}

	}
}
