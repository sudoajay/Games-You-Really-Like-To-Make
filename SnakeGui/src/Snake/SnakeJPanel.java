package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JProgressBar;

public class SnakeJPanel extends JPanel implements KeyListener, ActionListener {
	public Boolean gameStart = false, firstTime = true, firstTimeRun = true, nowOn = false ,touches = false ;
	public String[] arrayForMode = { "Classic", "Thunder" };
	public int noForMode, delay = 100, score, highScore, getModeCheck, progressTime;
	public Timer timer;
	public SnakeFood snakeFood;
	public Snake snake;
	public Random random;
	public JProgressBar progressBar;
	public Thread threadProgressBar;

	public SnakeJPanel() {
		setPreferredSize(new Dimension(800, 600));
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		random = new Random();
		gameStart();
		timer = new Timer(delay, this);
		setLayout(null);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!gameStart) {
			g.setColor(Color.GRAY);
			g.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 32));
			g.drawString(" Snake ", getWidth() / 2 - 60, 40);
			g.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 26));
			g.drawString("Press Space To Start The Game", getWidth() / 2 - 200, getHeight() / 2 - 30);
			g.drawString(" << Game Mode - " + arrayForMode[noForMode] + " >>", getWidth() / 2 - 180,
					getHeight() / 2 + 5);
			g.drawString("Credited By : Ajay Singh ", getWidth() - 300, getHeight() - 20);

		} else {
			snakeFood.paint(g);
			snake.paint(g);
			g.setColor(Color.GRAY);
			g.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 20));
			g.drawString("S- " + score, getWidth() / 2, 30);
			g.drawString("HS- " + highScore, getWidth() / 2 - 90, 30);
		}
		if (noForMode == 1) {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, 5, getHeight());
			g.fillRect(getWidth() - 5, 0, 5, getHeight());
			g.fillRect(0, 0, getWidth(), 5);
			g.fillRect(0, getHeight() - 5, getWidth(), 5);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		snake.KeyPressed(key);
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (key == KeyEvent.VK_SPACE) {
			if (firstTime) {
				checkForHighScore();
				gameStart = true;
				firstTime = false;
				timer.start();
				repaint();
				ProgressBarThread();

			}
			if (firstTimeRun) {
				snake.runSnake.start();
				snake.threadSound.start();
				snake.threadBigSound.start();
				threadProgressBar.start();
				firstTimeRun = false;
			}

		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			if (!gameStart) {
				if (noForMode < 1) {
					noForMode++;
					getModeCheck = 0;
				}
			}
			repaint();

		}
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			if (!gameStart) {
				if (noForMode > 0) {
					noForMode--;
					getModeCheck = 1;
				}
			}
			repaint();

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void gameStart() {
		snake = new Snake(150, 150, this);
		snakeFood = new SnakeFood(random.nextInt(700) + 40, random.nextInt(500) + 40, snake);
		snake.Create();
		snake.IntersectSnake();
		snake.SoundEffect();
		snake.SoundEffectBigFood();
		repaint();
		ProgressBar();

	}

	public void ProgressBar() {
		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setBounds(61, 12, 148, 14);
		progressBar.setVisible(false);
		add(progressBar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Update();
		repaint();

	}

	public void Update() {
		getModeCheck = noForMode;
		snakeFood.Move();
		snake.SnakeMove();
		if (nowOn) {
			progressBar.setVisible(true);
		}
		else{
			progressBar.setVisible(false);
		}

	}

	public void Restart() {

		snake.snakeArray.clear();
		gameStart = false;
		firstTime = true;
		snake.Create();

	}

	public void checkForHighScore() {
		if (getModeCheck == noForMode) {
			if (highScore < score) {
				highScore = score;
			}
			score = 0;

		} else {
			highScore = 0;
			score = 0;
		}
	}

	public void ProgressBarThread() {
		threadProgressBar = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (nowOn) {
						if (progressTime <= 100) {
							progressBar.setValue(progressTime);
							progressTime += 10;
							try {
								Thread.sleep(300);
							} catch (Exception e) {
							}
						}
						else{
							progressBar.setVisible(false);
							nowOn = false;
							snake.count=0;
							touches= false;
							progressBar.setValue(0);
							progressTime = 0;
							
						}
					}
					
				}
			}
		});
	}
}
