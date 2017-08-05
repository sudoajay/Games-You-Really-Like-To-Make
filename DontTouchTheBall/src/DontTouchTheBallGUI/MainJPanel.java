package DontTouchTheBallGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
import java.sql.Savepoint;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainJPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, ActionListener {
	public Boolean gameStart = false;
	public Player player;
	public Bullet bullet;
	public Ball ball;
	public int delay = 6, life = 3, mX;
	public Timer timer;
	public BufferedImage BgImage, playerImage, bulletImage, cursorImg, ballImage;
	public ImageIcon restartImage;

	public MainJPanel() {
		setPreferredSize(new Dimension(1300, 700));
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		timer = new Timer(delay, this);
		GameStart();
		ImageLoad();
		ImageHider();
	}

	public void ImageHider() {
		cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
	}

	public void ImageLoad() {
		try {
			restartImage = new ImageIcon("/home/sudoajay/Documents/workspace/DontTouchTheBall/src/Images/Restart.png");
			BgImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/DontTouchTheBall/src/Images/BgImage.jpg"));
			playerImage = ImageIO
					.read(new File("/home/sudoajay/Documents/workspace/DontTouchTheBall/src/Images/Player.png"));
			bulletImage = ImageIO
					.read(new File("/home/sudoajay/Documents/workspace/DontTouchTheBall/src/Images/Bullet.png"));
			ballImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/DontTouchTheBall/src/Images/Ball.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void GameStart() {
		player = new Player(this);
		bullet = new Bullet(this);
		player.CreateAPlayer();
		ball = new Ball(bullet, player, this);
		ball.CreateBalls(600, 100, 150, 150);
		ball.saveSpeed.add(-1);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!gameStart) {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Gargi", Font.BOLD, 22));
			g.drawString("Press Space To Start The Game ", getWidth() / 2 - 150, getHeight() / 2);
			g.drawString("Credited By : Ajay Singh ", getWidth() / 2 - 110, getHeight() / 2 + 30);
		} else {
			g.drawImage(BgImage, 0, 0, getWidth(), getHeight(), this);
			player.paint(g);
			bullet.paint(g);
			ball.paint(g);

			g.setColor(Color.ORANGE);
			g.setFont(new Font("Gargi", Font.BOLD, 24));
			g.drawString("Life - " + life, 100, getHeight() - 20);
			g.drawString("Bullet Count - " + bullet.bulletCountLimit, 500, getHeight() - 20);
			g.drawString("Ball Count - " + ball.rectBall.size(), getWidth() - 200, getHeight() - 20);

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (key == KeyEvent.VK_SPACE) {
			if (!gameStart) {
				gameStart = true;
				timer.start();
				ball.moveBallThread.start();
				ball.ballCollisionWithBullet.start();
				ball.ballCollisionWithPlayer.start();
				repaint();
			} else {
				if (timer.isRunning()) {
					timer.stop();
				} else {
					timer.start();
				}
			}
		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {

			mX = 1;

		}
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {

			mX = -1;

		}
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			bullet.CreateABullet(player.rectPlayer.x, player.rectPlayer.y);
			repaint();

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			mX = 0;
			repaint();
		}
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			mX = 0;
			repaint();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		player.rectPlayer.x = e.getX();

		if (player.rectPlayer.x <= 0) {

			player.rectPlayer.x = 0;
		} else if (player.rectPlayer.x + player.rectPlayer.width >= getWidth()) {
			player.rectPlayer.x = getWidth() - player.rectPlayer.width;
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		player.rectPlayer.x = e.getX();

		if (player.rectPlayer.x <= 0) {

			player.rectPlayer.x = 0;
		} else if (player.rectPlayer.x + player.rectPlayer.width >= getWidth()) {
			player.rectPlayer.x = getWidth() - player.rectPlayer.width;
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!gameStart) {
			gameStart = true;
			timer.start();
			repaint();
		} else {
			if (bullet.bulletReach) {
				bullet.CreateABullet(player.rectPlayer.x, player.rectPlayer.y);
				repaint();
				bullet.bulletReach = false;
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
		if (!gameStart) {
			gameStart = true;
			repaint();
		} else {
			if (bullet.bulletReach) {
				bullet.CreateABullet(player.rectPlayer.x, player.rectPlayer.y);
				repaint();
				bullet.bulletReach = false;
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		bullet.BulletUpdate();
		ball.BallUpdate();
		
		repaint();
		
		if (ball.rectBall.size() == 0) {
			GameOver(2);
		}
		if (bullet.bulletCountLimit < 1) {

			GameOver(3);
		}
	}

	public void GameOver(int getNo) {
		int get;
		timer.stop();

		if (getNo == 1) {
			get = JOptionPane.showConfirmDialog(null, "You Just Sucked up with a Ball ", "Your Game Over !",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartImage);
		} else if (getNo == 2) {
			get = JOptionPane.showConfirmDialog(null,
					" Congrats  You Destroy All Balls With only " + (50 - bullet.bulletCountLimit) + " Bullet",
					"Game Restart !", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartImage);
		} else {
			get = JOptionPane.showConfirmDialog(null, " You out Of bullet ! Play Again ", "Game Restart !",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartImage);

		}

		if (get == 0) {

			Restart();
		} else {
			System.exit(0);
		}
	}

	public void Restart() {
		ball.saveSpeed.clear();
		ball.saveUpAndDown.clear();
		life = 3;
		bullet.bulletCountLimit = 50;
		ball.rectBall.clear();
		player.CreateAPlayer();
		ball.CreateBalls(600, 100, 150, 150);
		ball.saveSpeed.add(-1);
		timer.start();

	}
}
