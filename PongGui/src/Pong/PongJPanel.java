package Pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class PongJPanel extends JPanel implements ActionListener {
	public int scoreLimit = 1, botLevel, player1Score, player2Score, player1Y = 220, player2Y = 220, delay =7 ;
	public boolean gameStart, ballIsThere, firstTime, onetimePeddlar, w, s, up, down;
	public Timer timer;
	public Random random;
	public PongPaddle paddle1, paddle2;
	public PongBall ball;
	public Icon restartIcon;

	public PongJPanel() {
		setPreferredSize(new Dimension(650, 550));
		setBackground(Color.BLACK);
		setLayout(null);
		addKeyListener(new Listener());
		setFocusable(true);
		GameStart();
		timer = new Timer(delay, this);
		random = new Random();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!gameStart) {
			// Game text
			g.setColor(Color.GRAY);
			g.setFont(new Font("Gargi", Font.BOLD, 22));
			g.drawString("Press Space To Start The Game ", 180, 236);
			g.drawString("Credited By : Ajay Singh ", 370, 530);
			g.drawString("<< Score Limit : " + scoreLimit + " >>", 220, 270);
		} else {
			// Game Ground
			g.setColor(Color.WHITE);
			g.setFont(new Font("Gargi", Font.BOLD, 30));
			g.drawOval(getWidth() / 2 - 95, getHeight() / 2 - 100, 200, 200);
			g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
			g.drawString("" + player1Score, getWidth() / 2 - 50, 100);
			g.drawString("" + player2Score, getWidth() / 2 + 35, 100);

			// paddle
			paddle1.paint(g);
			paddle2.paint(g);

			// ball
			ball.paint(g);
		}
	}

	public void Update() {
		paddle1.move(1);
		paddle2.move(2);
		ball.move();
	}

	public void actionPerformed(ActionEvent e) {
		Update();
		repaint();

	}

	public void GameStart() {
		restartIcon = new ImageIcon("/home/sudoajay/Documents/workspace/PongGui/src/Images/Restart.png");
		ball = new PongBall(this);
		paddle1 = new PongPaddle(ball, KeyEvent.VK_W, KeyEvent.VK_S, 1);
		paddle2 = new PongPaddle(ball, KeyEvent.VK_UP, KeyEvent.VK_DOWN, 2);

	}

	public class Listener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			paddle1.keyPressed(e.getKeyCode());
			paddle2.keyPressed(e.getKeyCode());
			if (key == KeyEvent.VK_ESCAPE)
				System.exit(0);
			if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {

				scoreLimit++;
				repaint();
			}

			if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {

				if (scoreLimit > 1) {
					scoreLimit--;
					repaint();
				}

			}

			if (key == KeyEvent.VK_SPACE) {
				if (!firstTime) {
					gameStart = true;
					repaint();
					timer.start();
				}
			}
			
			
			if (key == KeyEvent.VK_BACK_SPACE) {
				gameStart = false;
				repaint();
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {

			paddle1.keyReleased(e.getKeyCode());
			paddle2.keyReleased(e.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	public void Restart() {
			gameStart = false;
			scoreLimit = 1;
			player1Score = 0;
			player2Score = 0;
			botLevel = 0;
			repaint();
	}

}
