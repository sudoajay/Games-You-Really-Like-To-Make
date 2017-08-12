package NinjaBallPackage;

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
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel implements KeyListener, ActionListener {
	public BufferedImage cursorImg, ballImage, paddlerImage;
	public Icon restartImage;
	public boolean gameStart;
	public int life = 3, delay = 10, stage, backWardSpeed = 20;
	public Layout layout;
	public Timer timer;
	public Ball ball;

	public MainPanel() {
		setPreferredSize(new Dimension(800, 600));
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		timer = new Timer(delay, this);
		MouseTransparent();
		GameStart();
		ImageLoad();
	}

	public void MouseTransparent() {
		cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
	}

	public void GameStart() {
		layout = new Layout(this);
		ball = new Ball(this, layout);
	}

	public void ImageLoad() {
		try {
			restartImage = new ImageIcon("/home/sudoajay/Documents/workspace/Ninja Ball/src/Images/Restart.png");
			ballImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Ninja Ball/src/Images/Ball.png"));
			paddlerImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Ninja Ball/src/Images/Paddler.png"));

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
			setBackground(Color.decode("#30baff"));

			layout.paint(g);
			ball.paint(g);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Gargi", Font.BOLD, 25));
			g.drawImage(ballImage, 20, 10, 25, 25, null);
			g.drawString("X" + life, 50, 30);
			g.drawString("" + stage, getWidth() - 50, 30);
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
				ball.fallThread.start();

			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		ball.keyReleased(key);
		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ball.BallUpdate();
		if (ball.rectBall.y >= 650) {

		}
		WhichStage();

		if (ball.rectBall.y >= 650) {

			if (life > 1) {
				stageDied(0);
			} else {

				GameOver(1);
			}
		}

		repaint();

	}

	public void WhichStage() {
		for (int i = 0; i < layout.rectBricks.size(); i++) {
			if (layout.rectBricks.get(74).x <= 150)
				stage = 3;

			else if (layout.rectBricks.get(52).x <= 150)
				stage = 2;

			else if (layout.rectBricks.get(40).x <= 150)
				stage = 1;

		}
	}

	public void stageDied(int get) {
		if (stage == 3) {
			get = 74;
		}
		else if (stage == 2) {
			get = 54;
		}
		else if (stage == 1) {
			get = 37;
		}
		if (layout.rectBricks.get(get).x <= 150) {
			for (int i = 0; i < layout.movingDownBricks.size(); i++) {
				layout.movingDownBricks.get(i).x += backWardSpeed;
			}
			for (int i = 0; i < layout.fallDownBricks.size(); i++) {
				layout.fallDownBricks.get(i).x += backWardSpeed;
			}
			for (int i = 0; i < layout.extraBricks.size(); i++) {
				layout.extraBricks.get(i).x += backWardSpeed;
			}
			for (int i = 0; i < layout.standBricks.size(); i++) {
				layout.standBricks.get(i).x += backWardSpeed;
			}
			for (int i = 0; i < layout.rectBricks.size(); i++) {
				layout.rectBricks.get(i).x += backWardSpeed;
			}

		} else {
			if (stage == 2) {
				layout.fallDownBricks.clear();
				layout.MakeFall(3);
			}
			life--;
			ball.rectBall.y = 0;

		}

	}

	public void GameOver(int getNo) {
		life = 0;
		repaint();
		int get;
		timer.stop();
		if (getNo == 1) {
			get = JOptionPane.showConfirmDialog(null, "Your Stage Score is " + stage + "  Play Again",
					"Your Game Over !", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartImage);
		} else {
			get = JOptionPane.showConfirmDialog(null, "Your have Completed all Stages", "Your Game Over !",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartImage);
		}
		if (get == 0) {

			Restart();
		} else {
			System.exit(0);
		}
	}

	public void Restart() {
		layout.AllClear();
		ball.CreatedAnBall();
		layout.CreatedAnLayout();
		life = 3;
		stage = 0;
		timer.start();

	}
}
