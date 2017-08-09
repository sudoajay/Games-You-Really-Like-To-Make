package JumpOverPaddlePackage;

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

public class MainPanel extends JPanel implements KeyListener , ActionListener {

	public boolean gameStart;
	public BufferedImage cursorImg, bGImage , ballImage , paddlerImage;
	public int score , delay = 15;
	public Icon congratsImages;
	public Ball ball;
	public Paddler paddler;
	public Timer timer;
	public MainPanel() {
		setPreferredSize(new Dimension(900, 600));
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		ImageHider();
		ImageLoad();
		GameStart();
		timer= new Timer(delay, this);
	}

	public void ImageHider() {
		cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
	}
	public void GameStart() {
		
		paddler = new Paddler(this);
		ball = new Ball(this , paddler );
	}

	public void ImageLoad() {
		try {
			bGImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Jump Over Paddler/src/Images/BgImage.jpg"));
			ballImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Jump Over Paddler/src/Images/Ball.png"));
			paddlerImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Jump Over Paddler/src/Images/Paddler.png"));
			congratsImages = new ImageIcon("/home/sudoajay/Documents/workspace/Jump Over Paddler/src/Images/congrats.png");
		} catch (Exception e) {
			e.printStackTrace();		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!gameStart) {
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Gargi", Font.BOLD, 22));
			g.drawString("Press Space To Start The Game ", getWidth() / 2 - 150, getHeight() / 2);
			g.drawString("Credited By : Ajay Singh ", getWidth() / 2 - 110, getHeight() / 2 + 30);
		} else {
			g.drawImage(bGImage, 0, 0, getWidth(), getHeight(), null);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Gargi", Font.BOLD, 22));
			g.drawString("Stage - " +score, getWidth()/2-80, 30);
			
			ball.paint(g);
			paddler.paint(g);
			
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
				
				paddler.threadForPaddler.start();
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
		ball.BallUpdate();
	
		repaint();
		
	}
	public void NextStage() {
		timer.stop();
		score++;
		int get;
		if(score!=3) {
		get = JOptionPane.showConfirmDialog(null, "Click Yes To Go To Next Level " , " Congrats ",
				JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_OPTION, congratsImages);
		if (get == 0) {
			timer.start();
			ball.jump = false;
			paddler.animationSpeed.clear();
			paddler.paddlerRect.clear();
			paddler.CreatedAnPaddle(score);
			ball.CreatedAnBall();
			
			} else {
				System.exit(0);
			}
		}else {
			get = JOptionPane.showConfirmDialog(null, "You Completed all Stages " , " Congrats ! ",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, congratsImages);
			if (get == 0) {
				score = 1;
				paddler.stage = 1;
				timer.start();
				ball.jump = false;
				paddler.animationSpeed.clear();
				paddler.paddlerRect.clear();
				paddler.CreatedAnPaddle(score);
				ball.CreatedAnBall();
				
			} else {
				System.exit(0);
			}
		}
	}
}
