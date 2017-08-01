package BallGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BallBreakerJPanel extends JPanel
		implements KeyListener, MouseListener, MouseMotionListener, ActionListener {

	public BufferedImage lifeImage;
	public Icon restartImage;
	public Icon bestImage;
	public Icon congratsImage;
	public Paddler paddler;
	public Ball ball;
	public Boxes box;
	public Power power;
	public boolean oneTime;
	public int score, life = 5, level = 1, delay = 7, highScore;
	public Timer timer;

	public BallBreakerJPanel() {
		setPreferredSize(new Dimension(900, 700));
		setBackground(Color.BLACK);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
		timer = new Timer(delay, this);
		mainBox();
		ImageLoad();

	}

	public void mainBox() {
		paddler = new Paddler(this);
		power = new Power(paddler, this);
		ball = new Ball(this, paddler, power);
		box = new Boxes(ball, this, power, paddler);
		GameLevels();

	}

	public void ImageLoad() {
		try {
			lifeImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/BallBrickBreaker/src/Images/Life.png"));
			restartImage = new ImageIcon("/home/sudoajay/Documents/workspace/BallBrickBreaker/src/Images/Restart.png");
			congratsImage = new ImageIcon("/home/sudoajay/Documents/workspace/BallBrickBreaker/src/Images/congrats.png");
			bestImage = new ImageIcon("/home/sudoajay/Documents/workspace/BallBrickBreaker/src/Images/Best.png");

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// corner wall
		g.setColor(Color.decode("#808893"));
		g.fillRect(0, 30, 20, getHeight() - 60);
		g.fillRect(getWidth() - 20, 30, 20, getHeight() - 60);
		g.fillRect(0, 30, getWidth(), 20);
		if (!oneTime) {
			// GameStarting Text
			g.setColor(Color.GRAY);
			g.setFont(new Font("Gargi", Font.BOLD, 22));

			g.drawString("Press Space To Start The Game ", getWidth() / 2 - 150, getHeight() / 2);
			g.drawString("Credited By : Ajay Singh ", getWidth() / 2 - 110, getHeight() / 2 + 30);
			g.setFont(new Font("Gargi", Font.BOLD, 24));
			g.drawString("Ball-Breaker", getWidth() / 2 - 60, 80);
		} else {
			// after agme start
			g.setColor(Color.decode("#a31d1d"));

			g.setFont(new Font("Gargi", Font.BOLD, 18));

			g.drawImage(lifeImage, 20, 8, 20, 20, null);
			g.drawString("x" + life, 41, 25);
			g.drawString("Level : " + level, 80, 25);
			g.drawString("Score : " + score, 400, 25);
			g.drawString("HighScore : " + highScore, 720, 25);

			paddler.paint(g);
			box.paint(g);
			ball.paint(g);
			power.paint(g);

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		paddler.KeyPressed(key);
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (key == KeyEvent.VK_SPACE) {
			if (!oneTime) {
				oneTime = true;
				timer.start();

				repaint();

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		paddler.keyReleased(key);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!oneTime) {
			oneTime = true;
			timer.start();

			repaint();
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		paddler.MouseMovement(e);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Upadate();
		
		repaint();
	}

	public void Upadate() {
		paddler.PadlerUpdate();
		power.Update();
		ball.Update();

	}

	public void GameRestart() {
		if (highScore < score) {
			highScore = score;
			JOptionPane.showConfirmDialog(null, "Congrats You Make The Highest Score In this Game Ever ", "Highest Score Ever !",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_OPTION, bestImage);
		}
		score = 0;
		int get = JOptionPane.showConfirmDialog(null, "Are You Sure To Play Again ? ", "Game Restart !",
				JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartImage);
		if (get == 0) {
			level = 1;
			ball.nextLevel = true;
			GameLevels();
		
		
			box.LocationOfNewLevelBall();
			
			timer.start();
			repaint();

		} else {
			System.exit(0);
		}
	}

	public void GameLevels() {

		if (level == 1) {
			box.arrayForBoxes.add(new Boxes(-26, 200, 0, 0));
		} else if (level == 2) {
			
			box.arrayForBoxes.clear();
			box.arrayForBoxes.add(new Boxes(-26, 200, 0, 0));
		} else {
			
			box.arrayForBoxes.clear();
			box.arrayForBoxes.add(new Boxes(-26, 200, 0, 0));
		}
	}
}
