package ShootForYourLifePackage;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class MainPanel extends JPanel implements KeyListener, ActionListener, MouseMotionListener, MouseListener {
	public BufferedImage cursorImg, playerImg, bgImage, bigEnemy, smallEnemy, bulletImage;
	public Icon restartImage;
	public boolean gameStart, progress;
	public int score, delay = 10, centerX, centerY, bulletLeft = 80, count;
	public String scores, bulletLefts;
	public Player player;
	public Enemy enemy;
	public Bullet bullet;
	public Timer timer;
	public double imageAngleRad, mouseY, mouseX;
	public BulletAmmo ammo;
	public JProgressBar progressBar;
	public Thread runProgressThread;

	public MainPanel() {
		setPreferredSize(new Dimension(1000, 700));
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		addMouseMotionListener(this);
		addMouseListener(this);
		timer = new Timer(delay, this);
		ImageHider();
		ImageLoad();
		GameStart();
		ProgressBar();
	}

	public void ImageHider() {
		try {
			cursorImg = ImageIO
					.read(new File("/home/sudoajay/Documents/workspace/Shoot  For Your Life/src/Images/Mouse-Cursor.png"));

			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(getX(), getY()),
					"img");

			setCursor(blankCursor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void GameStart() {
		Run();
		player = new Player(this);
		ammo = new BulletAmmo(this, player);
		enemy = new Enemy(this, player);
		bullet = new Bullet(this, enemy);
	}

	public void ImageLoad() {
		try {
			playerImg = ImageIO
					.read(new File("/home/sudoajay/Documents/workspace/Shoot  For Your Life/src/Images/player.png"));
			bgImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Shoot  For Your Life/src/Images/bg.png"));
			smallEnemy = ImageIO
					.read(new File("/home/sudoajay/Documents/workspace/Shoot  For Your Life/src/Images/small-enemy.png"));
			bigEnemy = ImageIO
					.read(new File("/home/sudoajay/Documents/workspace/Shoot  For Your Life/src/Images/big-enemy.png"));
			bulletImage = ImageIO
					.read(new File("/home/sudoajay/Documents/workspace/Shoot  For Your Life/src/Images/Bullet.png"));
			restartImage = new ImageIcon("/home/sudoajay/Documents/workspace/Shoot  For Your Life/src/Images/Restart.png");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void ProgressBar() {
		progressBar = new JProgressBar(0, 20);
		progressBar.setValue(0);
		progressBar.setBounds(61, 12, 148, 14);
		progressBar.setVisible(false);
		add(progressBar);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!gameStart) {
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Gargi", Font.BOLD, 22));
			g.drawString("Press Space To Start The Game ", getWidth() / 2 - 150, getHeight() / 2);
			g.drawString("Credited By : Ajay Singh ", getWidth() / 2 - 110, getHeight() / 2 + 30);
		} else {
			g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
			bullet.paint(g);
			player.paint(g);
			enemy.paint(g);
			ammo.paint(g);
			g.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 20));
			g.setColor(Color.BLACK);
			scores = score + "";
			scores = ("00000" + scores).substring(scores.length(), scores.length() + 5);
			g.drawString(scores, 20, 25);
			bulletLefts = bulletLeft + "";
			bulletLefts = ("0000" + bulletLefts).substring(bulletLefts.length(), bulletLefts.length() + 4);
			g.drawString(bulletLefts, 920, 25);

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		player.KeyPressed(key);
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (key == KeyEvent.VK_SPACE) {
			if (!gameStart) {
				gameStart = true;
				enemy.roatedThread.start();
				runProgressThread.start();
				timer.start();
				repaint();
			}
			if (!timer.isRunning())
				timer.start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		player.keyReleased(key);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.playerUpdate();
		enemy.enemyUpdate();
		bullet.bulletUpdate();
		ammo.AmmoUpdate();

		repaint();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX() - player.playerRect.getX();
		mouseY = e.getY() - player.playerRect.getY();
		imageAngleRad = Math.atan2(mouseX, mouseY);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		mouseX = e.getX() - player.playerRect.getX();
		mouseY = e.getY() - player.playerRect.getY();
		imageAngleRad = Math.atan2(mouseX, mouseY);

		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (bulletLeft > 0) {
			bullet.CreatedAnBullet(player.playerRect.x, player.playerRect.y);
			bulletLeft--;
		}
		repaint();

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

	public void Run() {

		runProgressThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (progress) {
						if (count == 0) {
							progressBar.setVisible(true);
							ammo.CreateAnBulletAmmoBound(1);
						}
						count++;
						progressBar.setValue(count);
						if (count >= 20) {
							progress = false;
							ammo.CreateAnBulletAmmoBound(2);
							progressBar.setVisible(false);
							count = 0;
						}
					} else {
						if (!timer.isRunning()) {

							count++;
							if (count == ammo.random.nextInt(20) + 40) {
								progress = true;
								count = 0;
							}
						}
					}
					repaint();
				}

			}
		});
	}

	public void GameOver() {
		timer.stop();

		int get;

		get = JOptionPane.showConfirmDialog(null, "Your Game Over And Your Score is " + score, " Try Again ! ",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_OPTION, restartImage);
		if (get == 0) {
			Restart();

		} else {
			System.exit(0);
		}

	}

	public void Restart() {
		bulletLeft = 80;
		score = 0;
		player.CreateAnPlayer();
		
		bullet.CLoseAll();
		enemy.CLoseAll();
		ammo.ClearAll();
		timer.start();
	}
}
