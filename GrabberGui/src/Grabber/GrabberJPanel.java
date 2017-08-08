package Grabber;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GrabberJPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, ActionListener {
	public BufferedImage basketImage, appleImage;
	public Color green;
	public int  grab, delay = 10, total;
	public Rectangle basket;
	public boolean pass = true, start;
	public Thread appleThrowThread;
	public Thread appleRotateThread;
	public Thread soundThread;
	public ArrayList<AppleInfo> appleArray;
	public Timer timer;
	public Image image;
	public int count;
	public Icon restartIcon;
	public Clip clip;
	public boolean grabTheApple  ;
	public GrabberJPanel() {
		setPreferredSize(new Dimension(700, 700));
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		appleArray = new ArrayList<>();
		timer = new Timer(delay, this);
		Images();
		basket = new Rectangle();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		g.drawImage(image, 0, 0, this);
		
		for (int i = 0; i < appleArray.size(); i++) {
			g.drawImage(appleImage,appleArray.get(i).x, appleArray.get(i).y, appleArray.get(i).width, appleArray.get(i).height, this);
		}
		g.setColor(green);
		g.fillRect(0, getHeight() - 70, getWidth(), 70);

		g.setColor(Color.GRAY);
		g.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 25));
		g.drawString("Grab : " + grab / 17, 120, getWidth() - 30);
		g.drawString("Total : " + total, getHeight() - 250, getWidth() - 30);

		g.drawImage(basketImage, basket.x, basket.y, basket.width, basket.height, this);

	}

	public void Images() {
		green = Color.decode("#63ff7d");

		try {
			restartIcon = new ImageIcon("/home/sudoajay/Documents/workspace/GrabberGui/src/Images/Restart.png");
			image = Toolkit.getDefaultToolkit()
					.createImage("/home/sudoajay/Documents/workspace/GrabberGui/src/Images/Bg.gif");
			basketImage = ImageIO.read(new File("/home/sudoajay/Documents/workspace/GrabberGui/src/Images/Basket.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (key == KeyEvent.VK_SPACE) {
			if (pass) {
				basket.setBounds(100, getHeight() - 143, 80, 80);
				repaint();
				start = true;
				AppleThrow();
				AppleRotate();
				SoundEffect();
				timer.start();
				appleThrowThread.start();
				appleRotateThread.start();
				soundThread.start();
				pass = false;
			}
		}
		if (key == KeyEvent.VK_R)
			Restart();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (pass) {
			basket.setBounds(100, getHeight() - 143, 80, 80);
			repaint();
			start = true;
			AppleThrow();
			AppleRotate();
			SoundEffect();
			timer.start();
			appleThrowThread.start();
			appleRotateThread.start();
			soundThread.start();
			pass = false;
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

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

		if (e.getX() >= 676) {
			basket.x = 625;

		} else if (e.getX() <= 25) {

			basket.x = -5;
		} else {
			basket.x = e.getX() - 35;

		}
		repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		if (e.getX() >= 676) {
			basket.x = 625;

		} else if (e.getX() <= 25) {

			basket.x = -5;
		} else {
			basket.x = e.getX() - 35;

		}
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < appleArray.size(); i++) {
			appleArray.get(i).Update();
			repaint();
		}
		
	}

	public void AppleThrow() {
		Random random = new Random();
		appleThrowThread = new Thread(new Runnable() {
			@Override
			public synchronized void run() {

				for (count = 0; count < 100;) {

					if (start) {
						int get = random.nextInt(3) + 3;
						for (int i = 0; i < get; i++) {
							appleArray.add(new AppleInfo(random.nextInt(650), 0, 40, 40));
							repaint();
							try {
								Thread.sleep(450);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();

							}
							count++;
						}
						try {
							Thread.sleep(500);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
				timer.stop();
				int getInput = JOptionPane.showConfirmDialog(null, "Your Score - " +grab/17 + " out of  100 Apple" , "Restart The Game ",
						JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, restartIcon);
				if (getInput == 0) {
					Restart();
				}
				else{
					System.exit(0);
				}
			}
		});
	}

	public void AppleRotate() {
		appleRotateThread = new Thread(new Runnable() {

			@Override
			public  void run() {
				while (true) {
					try {
						appleImage = ImageIO
								.read(new File("/home/sudoajay/Documents/workspace/GrabberGui/src/Images/Apple1.png"));
						repaint();
						Thread.sleep(300);
						appleImage = ImageIO
								.read(new File("/home/sudoajay/Documents/workspace/GrabberGui/src/Images/Apple2.png"));
						repaint();
						Thread.sleep(300);
						appleImage = ImageIO
								.read(new File("/home/sudoajay/Documents/workspace/GrabberGui/src/Images/Apple3.png"));
						repaint();
						Thread.sleep(300);
						appleImage = ImageIO
								.read(new File("/home/sudoajay/Documents/workspace/GrabberGui/src/Images/Apple4.png"));
						repaint();
						Thread.sleep(300);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
	}

	public void Restart() {
		timer.start();
		count = 0;
		total = 0;
		grab = 0;
		appleArray.clear();
		repaint();

	}

	public void SoundEffect() {
		soundThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						if(grabTheApple){
					try {
						Thread.sleep(300);
						File file = new File("/home/sudoajay/Documents/workspace/GrabberGui/src/Images/Sound.wav");

					AudioInputStream ais = AudioSystem.getAudioInputStream(file);
					AudioFormat format = ais.getFormat();
					DataLine.Info info = new DataLine.Info(Clip.class, format);
					clip = (Clip) AudioSystem.getLine(info);
					clip.open(ais);
					clip.loop(Clip.LOOP_CONTINUOUSLY);		
					clip.start();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				grabTheApple = false;
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				clip.stop();
			}
				}
			}
		});
	}

	class AppleInfo {
		public int x, y, width, height, speed;

		public AppleInfo(int newX, int newY, int newWidth, int newHeight) {
			this.x = newX;
			this.y = newY;
			this.width = newWidth;
			this.height = newHeight;

		}

		public void Update() {

			if (y <= 50) {
				speed = 2;
				y += speed;

			} else {
				speed = 8;
				y += speed;

			}
			repaint();
			if (basket.intersects(new Rectangle(x, y, width, height))) {
				grabTheApple = true;
				grab++;
				repaint();
			}
			//grabTheApple = true;
			if (y >= 630) {
				total = appleArray.size() -2;
			repaint();
			}
		}
	}
	

}
