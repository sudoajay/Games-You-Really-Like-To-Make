package FallDownGui;

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

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel implements KeyListener, ActionListener {

	public boolean gameStart;
	public BufferedImage BGImage, ballImage;
	public Icon restartImage;
	public int score, delay = 10;
	public Ball ball;
	public Timer timer;
	public FallDownRectangle fall;
	public Thread runThreadScore;

	public MainPanel() {
		addKeyListener(this);
		setFocusable(true);
		setPreferredSize(new Dimension(700, 600));
		setBackground(Color.BLACK);
		timer = new Timer(delay, this);
		ImageLoad();
		GameStart();
	}

	public void GameStart() {

		fall = new FallDownRectangle(this);
		ball = new Ball(this, fall);
		Run();
	}

	public void ImageLoad() {
		try {
			restartImage = new ImageIcon("/home/sudoajay/Documents/workspace/Fall Down/src/Images/Restart.png");
			BGImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Fall Down/src/Images/BGImages.jpg"));
			ballImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Fall Down/src/Images/Ball.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!gameStart) {
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Gargi", Font.BOLD, 22));
			g.drawString("Press Space To Start The Game ", getWidth() / 2 - 150, getHeight() / 2);
			g.drawString("Credited By : Ajay Singh ", getWidth() / 2 - 110, getHeight() / 2 + 30);
		} else {
			g.drawImage(BGImage, 0, 0, 700, 600, null);

			ball.paint(g);
			fall.paint(g);

			g.setColor(Color.decode("#42f486"));
			g.fillRect(0, 530, getWidth(), 70);
			g.setFont(new Font("Gargi", Font.BOLD, 22));
			g.setColor(Color.RED);
			g.drawString("Score : " + score, getWidth() / 2 - 70, getHeight() - 30);

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		ball.KeyPressed(key);
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (key == KeyEvent.VK_SPACE) {
			if (!gameStart) {
				gameStart = true;
				timer.start();
				runThreadScore.start();
				repaint();

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		ball.keyReleased(key);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ball.BallUpdates();
		fall.fallItUpdate();
		repaint();

	}

	public void gameRestart() {
		int get;
		timer.stop();
		get = JOptionPane.showConfirmDialog(null, "You Die & Your Score is " + score, "Your Game Over !",
				JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartImage);
		if (get == 0) {
			Restart();
		} else {
			System.exit(0);
		}
	}

	public void Restart() {
		score = 0;
		ball.CreatedAnBall();
		fall.fallItRect.clear();
		fall.GameStart();
		timer.start();
	}
	public void Run() {
		runThreadScore = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
						score++;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		});
	}
}
