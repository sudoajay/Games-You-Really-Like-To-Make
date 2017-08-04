package MoveForwardPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel implements KeyListener, ActionListener {
	public boolean gameStart;
	public int score;
	public BufferedImage BGImage, ballImage;
	public Icon restartImage;
	public Ball ball;
	public Paddler paddler;
	public int delay = 15;
	public Timer timer;
	public Points point;

	public MainPanel() {
		setPreferredSize(new Dimension(1300, 700));
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		timer = new Timer(delay, this);
		ImageLoad();
		GameStart();
	}

	public void ImageLoad() {
		try {
			BGImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Move Forward/src/Images/BGImages.jpg"));
			ballImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Move Forward/src/Images/Ball.png"));
			restartImage = new ImageIcon("/home/sudoajay/Documents/workspace/Move Forward/src/Images/Restart.png");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void GameStart() {

		paddler = new Paddler(this);
		ball = new Ball(this, paddler);
		point = new Points(this, ball);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!gameStart) {
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Gargi", Font.BOLD, 22));
			g.drawString("Press Space To Start The Game ", getWidth() / 2 - 150, getHeight() / 2);
			g.drawString("Credited By : Ajay Singh ", getWidth() / 2 - 110, getHeight() / 2 + 30);
		} else {
			g.drawImage(BGImage, 0, 0, getWidth(), getHeight(), null);

			ball.paint(g);
			paddler.paint(g);
			point.paint(g);
			g.setColor(Color.decode("#42f486"));
			g.fillRect(0, 630, getWidth(), 70);
			g.setFont(new Font("Gargi", Font.BOLD, 22));
			g.setColor(Color.RED);
			g.drawString("Points : " + score, getWidth() / 2 - 70, getHeight() - 30);

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		ball.keyPressed(key);

		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (key == KeyEvent.VK_SPACE) {
			if (!gameStart) {
				gameStart = true;
				timer.start();

				repaint();

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		ball.KeyReleased(key);

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ball.ballUpdate();
		paddler.PaddlerUpdate();
		point.PointUpdate();
		repaint();
		if (ball.ballRect.y == 650)
			gameRestart();

	}

	public void gameRestart() {
		int get;
		timer.stop();
		get = JOptionPane.showConfirmDialog(null, "You Die & Your Points is " + score, " Your Game Over !",
				JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartImage);
		if (get == 0) {
			Restart();
		} else {
			System.exit(0);
		}
	}

	public void Restart() {
		score = 0;
		ball.CreateAnBall();
		paddler.arrayForPaddler.clear();
		paddler.StartingOfGame();
		point.enemyBall.clear();
		point.gamePointBall.clear();
		point.GameStarting();
		timer.start();
	}
}
