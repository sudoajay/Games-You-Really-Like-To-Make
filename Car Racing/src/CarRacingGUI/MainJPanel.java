package CarRacingGUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainJPanel extends JPanel implements KeyListener, ActionListener {
	public boolean gameStart, gameStop = true;
	public Layout layout;
	public BufferedImage grassImage, playerCar, sandImage;
	public int delay = 4, timing = 50;
	public Timer timer;
	public PlayerCar car;
	public OtherCar otherCar;
	public Thread threadForTiming;
	public Tree tree;
	public Icon restartImage;

	public MainJPanel() {
		setPreferredSize(new Dimension(650, 700));
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		timer = new Timer(delay, this);
		GameStart();
		ImageLoad();
	}

	public void ImageLoad() {
		try {
			restartImage = new ImageIcon("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Restart.png");
			grassImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Grass.jpg"));
			playerCar = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/PlayerCar.png"));
			sandImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/Car Racing/src/Images/Sand.jpg"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void GameStart() {
		RunTiming();
		layout = new Layout(this);
		otherCar = new OtherCar(this, layout);
		tree = new Tree(this, layout);
		car = new PlayerCar(this, layout, tree, otherCar);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if (!gameStart) {
			g.setColor(Color.GREEN);
			g.setFont(new Font("Gargi", Font.BOLD, 22));
			g.drawString("Press Space To Start The Game ", getWidth() / 2 - 150, getHeight() / 2);
			g.drawString("Credited By : Ajay Singh ", getWidth() / 2 - 110, getHeight() / 2 + 30);
		} else {
			layout.paint(g);
			otherCar.paint(g);
			car.paint(g);
			tree.paint(g);

			g.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(3));
			g.drawRect(305, 30, 60, 50);
			g.setColor(Color.RED);
			g.setFont(new Font("Gargi", Font.BOLD, 24));
			g.drawString("" + timing, 320, 65);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		car.KeyPressed(key);
		repaint();
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (key == KeyEvent.VK_SPACE) {
			if (!gameStart) {
				gameStart = true;
				threadForTiming.start();
				setBackground(Color.decode("#4d5146"));
				repaint();
			}
			if (!timer.isRunning()) {
				timer.start();
			} else {
				timer.stop();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (layout.level == 1 || layout.level == 2) {
			layout.lineOnRoadMovingUpdate();
			otherCar.OtherCarUpdate();

			tree.TreeUpdate();
			car.playerCarUpdate();
			
		} else {
			ClearAll();
			car.PlayerCarRect.y--;
			
			if (car.PlayerCarRect.y == -200) {
				car.PlayerCarRect.x = 1000;
				GameRestart(4);
			}
		}
		repaint();
	}

	public void RunTiming() {
		threadForTiming = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(2);

						if (!gameStop) {

							if (timing == 0) {
								GameRestart(1);
							}
							timing--;

							Thread.sleep(1500);
						} else {

							if (car.PlayerCarRect.y == 500) {
								timer.start();
								gameStop = false;
							}
							car.PlayerCarRect.y++;
							if (car.PlayerCarRect.y == 800 ||car.PlayerCarRect.x == 1000 )
								timer.stop();
						}
						repaint();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	public void GameRestart(int getNo) {
		int get;
		gameStop = true;
		
		if (getNo == 1) {
			get = JOptionPane.showConfirmDialog(null, "You Just Run Of Time ! Play Again ", "Your Game Over !",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartImage);
		} else if (getNo == 2) {
			get = JOptionPane.showConfirmDialog(null, " You Just Kill By Tree ! Play Again ", "Your Game Over !",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartImage);
		} else if (getNo == 3) {
			get = JOptionPane.showConfirmDialog(null, " You Just Kill by An Accident ! Play Again ", "Your Game Over !",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartImage);

		} else {
			get = JOptionPane.showConfirmDialog(null, " You Complete The Game In time Welldone", "Game Restart !",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartImage);

		}

		if (get == 0) {
			restartGame();
		} else {
			System.exit(0);
		}

	}

	public void restartGame() {

		timing = 50;
		ClearAll();
		layout.counting = 0;
		layout.level = 1;
		layout.arrayForBordersLeft.clear();
		layout.arrayForBordersCenter.clear();
		layout.arrayForBordersRigth.clear();

		otherCar.OtherCarCreated();
		tree.CreatedTree();
		car.CreatedPlayerCar();
		layout.CreateBorders();
		timer.start();

	}
	public void ClearAll() {
		otherCar.saveAsPerLevel = 3;
		otherCar.leftHandSide.clear();
		otherCar.saveLeftHandImages.clear();
		otherCar.saveLeftHandNo.clear();

		otherCar.rightHandSide.clear();
		otherCar.saveRightHandImages.clear();
		otherCar.saveRightHandNo.clear();

		tree.rectforTree.clear();
		tree.saveBufferImage.clear();

	
	}
}
